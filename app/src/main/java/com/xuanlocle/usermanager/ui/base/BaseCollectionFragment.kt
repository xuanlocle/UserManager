package com.xuanlocle.usermanager.ui.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.ui.adapter.RecyclerManager
import com.xuanlocle.usermanager.util.Constants
import kotlinx.android.synthetic.main.collection_base_fragment.*
import kotlinx.android.synthetic.main.collection_base_fragment.recyclerView

abstract class BaseCollectionFragment<VM : BaseViewModel> : BaseFragmentMVVM<VM>() {

    protected var mRecyclerManager: RecyclerManager<Any> = RecyclerManager()
    protected var mLayoutManager: LinearLayoutManager? = null
    protected abstract fun initCluster()
    protected var isLoadingMore = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initCluster()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayout(): Int = R.layout.collection_base_fragment

    protected fun addCluster(cluster: Any) {
        mRecyclerManager.addCluster(cluster)
    }

    protected open fun isSupportLoadMore(): Boolean {
        return false
    }

    private fun initRecyclerView(view: View, parcelableLayoutManager: Parcelable?) {
        mLayoutManager = LinearLayoutManager(view.context)

        parcelableLayoutManager?.let {
            mLayoutManager!!.onRestoreInstanceState(it)
        }

        recyclerView.adapter = mRecyclerManager.adapter
        recyclerView.layoutManager = mLayoutManager

        if (isSupportLoadMore()) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    if (!isLoadingMore && totalItemCount > 3  //skip for first insert item
                        && totalItemCount - lastVisibleItemPosition < 4
                    ) {
                        loadMore()
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var parcelableLayoutManager: Parcelable? = null
        if (savedInstanceState != null) {
            isLoadingMore = savedInstanceState.getBoolean(Constants.BundleKey.IS_LOADING_MORE)
            viewModel.restoreState(savedInstanceState)
            parcelableLayoutManager = savedInstanceState.getParcelable(Constants.BundleKey.LIST_STATE_KEY)
        }

        initRecyclerView(view, parcelableLayoutManager)
        vRefresh?.setOnRefreshListener { onRefreshStart() }
    }

    protected open fun loadMore() {

    }

    protected open fun onLoadMoreComplete() {

    }

    open fun onRefreshStart() {
        vRefresh?.isRefreshing = true
    }

    open fun onRefreshCompleted() {
        vRefresh?.isRefreshing = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constants.BundleKey.LIST_STATE_KEY, mLayoutManager?.onSaveInstanceState())
        outState.putBoolean(Constants.BundleKey.IS_LOADING_MORE, isLoadingMore)
        viewModel.saveState(outState)
    }
}