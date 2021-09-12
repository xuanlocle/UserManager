package com.xuanlocle.usermanager.ui.user_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.ui.adapter.item.LoadingRecyclerItem
import com.xuanlocle.usermanager.ui.base.BaseCollectionFragment
import com.xuanlocle.usermanager.ui.user_list.item.RepoRecyclerViewItem
import com.xuanlocle.usermanager.ui.user_list.item.UserRecyclerItemListener
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UserListFragment : BaseCollectionFragment<UserListViewModel>(), KodeinAware,
    UserRecyclerItemListener {

    companion object {
        fun newInstance(): UserListFragment {
            val args = Bundle()

            val fragment = UserListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val kodein by closestKodein()
    private val factory: UserListViewModelFactory by instance()

    override fun getLayout(): Int = R.layout.user_list_fragment
    override fun isSupportLoadMore(): Boolean = true

    override fun initCluster() {
        addCluster(RepoRecyclerViewItem::class)
    }

    override fun initObserve() {
        viewModel.repos.observe(viewLifecycleOwner, { repos ->
            onRefreshCompleted()
            repos?.let {
                mRecyclerManager.replace(
                    RepoRecyclerViewItem::class,
                    repos.map { RepoRecyclerViewItem(it, this) })
            }
        })

        viewModel.reposMore.observe(viewLifecycleOwner, { repos ->
            if (isLoadingMore) {
                onLoadMoreComplete()
                mRecyclerManager.append(RepoRecyclerViewItem::class,
                    repos.map { RepoRecyclerViewItem(it, this) })
            }
        })

        viewModel.isLoadingMore.observe(viewLifecycleOwner, {
            if (it && isLoadingMore == false) {
                isLoadingMore = true
                addLoadingMoreUpdate()
            }
        })
    }

    override fun init() {
        viewModel = ViewModelProvider(this, factory).get(UserListViewModel::class.java)
        viewModel.init()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState ?: viewModel.getRepositories()
    }

    override fun loadMore() {
        if (isLoadingMore)
            return

        addLoadingMoreUpdate()
        isLoadingMore = true
        viewModel.getMoreRepositories()
    }


    override fun onLoadMoreComplete() {
        if (isLoadingMore)
            removeLoadingMore()
        isLoadingMore = false
    }

    private fun addLoadingMoreUpdate() {
        mRecyclerManager.adapter?.append(LoadingRecyclerItem())
    }

    private fun removeLoadingMore() {
        mRecyclerManager.adapter?.remove(mRecyclerManager.adapter.itemCount - 1)
    }

    override fun onClickUserItem(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onRefreshStart() {
        super.onRefreshStart()
        viewModel.reloadRepositories()
    }
}