package com.xuanlocle.usermanager.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected abstract fun getLayout(): Int
    protected var isForeground: Boolean = false
    protected lateinit var mCompositeDisposable: CompositeDisposable


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAddCompositeDisposable()
        isForeground = true
    }

    protected open fun onAddCompositeDisposable(){
        mCompositeDisposable = CompositeDisposable()
    }

    override fun onDestroyView() {
        if (!mCompositeDisposable.isDisposed)
            mCompositeDisposable.clear()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        isForeground = true
    }

    override fun onPause() {
        super.onPause()
        isForeground = false
    }

    override fun onStop() {
        super.onStop()
        isForeground = false
    }

}
