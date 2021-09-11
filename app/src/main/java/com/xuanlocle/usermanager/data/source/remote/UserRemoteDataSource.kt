package com.xuanlocle.usermanager.data.source.remote

import com.xuanlocle.usermanager.data.model.UserRemoteModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult

interface UserRemoteDataSource {
    suspend fun fetchUsers(page: Int): BaseResult<List<UserRemoteModel>>
}