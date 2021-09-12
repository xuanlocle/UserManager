package com.xuanlocle.usermanager.data.source.remote

import com.xuanlocle.usermanager.data.model.UserProfileRemoteModel
import com.xuanlocle.usermanager.data.model.UserRemoteModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult

interface UserRemoteDataSource {
    suspend fun fetchUsers(sinceUserId: Int): BaseResult<List<UserRemoteModel>>
    suspend fun fetchUserProfile(userLoginId: String): BaseResult<UserProfileRemoteModel>
}