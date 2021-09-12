package com.xuanlocle.usermanager.ui.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xuanlocle.usermanager.widget.MutableLiveDataSingle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private lateinit var job: Job
    protected lateinit var uiScope: CoroutineScope
    protected lateinit var ioContext: CoroutineContext
    protected val showLoading = MutableLiveDataSingle<Boolean>()
    val showLoadingLiveData: LiveData<Boolean> get() = showLoading

    protected val showError = MutableLiveDataSingle<String>()
    val showErrorLiveData: LiveData<String> get() = showError

    fun init() {
        onCreate()
    }

    protected open fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job

    }

    fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
    }

    abstract fun saveState(outState: Bundle)
    abstract fun restoreState(savedInstanceState: Bundle)

}