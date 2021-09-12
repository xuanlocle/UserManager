package com.xuanlocle.usermanager.ui.base

import android.os.Bundle
import android.view.View
import com.xuanlocle.usermanager.R

abstract class BaseFragmentMVVM<VM : BaseViewModel> : BaseFragment() {

    protected lateinit var viewModel: VM

    override fun getLayout(): Int = R.layout.base_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserve()
    }

    abstract fun initObserve()

    override fun onDestroy() {
        super.onDestroy()
        if (this::viewModel.isInitialized)
            viewModel.onDestroy()
    }

    abstract fun init()

}
