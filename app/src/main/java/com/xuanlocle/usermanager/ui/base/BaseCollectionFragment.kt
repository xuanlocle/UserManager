package com.xuanlocle.usermanager.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.ui.adapter.RecyclerManager
import com.xuanlocle.usermanager.util.Constants
import kotlinx.android.synthetic.main.collection_base_fragment.*
import kotlinx.android.synthetic.main.collection_base_fragment.recyclerView

abstract class BaseCollectionFragment<VM : BaseViewModel> : BaseFragmentMVVM<VM>() {

    protected var mRecyclerManager: RecyclerManager<Any> = RecyclerManager()
    protected lateinit var mGridlayoutManager: GridLayoutManager
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

    open fun getSpanCount(): Int = 1

    protected open fun isSupportLoadMore(): Boolean {
        return false
    }

    private fun initRecyclerView(view: View) {
        mGridlayoutManager = GridLayoutManager(view.context, getSpanCount(), GridLayoutManager.VERTICAL, false);
        recyclerView.adapter = mRecyclerManager.adapter
        recyclerView.layoutManager = mGridlayoutManager

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
        initRecyclerView(view)
        vRefresh?.setOnRefreshListener { onRefreshStart() }


        if (savedInstanceState != null) {
            viewModel.restoreState(savedInstanceState)
            return
        }
    }

    protected open fun loadMore() {

    }
    protected open fun onLoadMoreComplete(){

    }

    open fun onRefreshStart() {
        vRefresh?.isRefreshing = true
    }

    open fun onRefreshCompleted() {
        vRefresh?.isRefreshing = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            Constants.BundleKey.LIST_STATE_KEY,
            mGridlayoutManager.onSaveInstanceState()
        );
        viewModel.saveState(outState)
    }
}