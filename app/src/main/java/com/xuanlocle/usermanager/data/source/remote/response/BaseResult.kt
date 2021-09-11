package com.xuanlocle.usermanager.data.source.remote.response

sealed class BaseResult<out T : Any> {
    class Success<T : Any>(val data: T?) : BaseResult<T>()
    class Error(val error: String) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}