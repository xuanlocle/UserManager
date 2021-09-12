package com.xuanlocle.usermanager.data.source.repository

import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.model.UserProfileModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult

interface UserRepository {
    suspend fun fetchUsers(sinceUserId: Int = 0): BaseResult<List<UserModel>>
    suspend fun removeOldData()

    suspend fun fetchUserProfileById(userLoginId: String): BaseResult<UserProfileModel>
    suspend fun fetchUpdateUserProfileById(userLoginId: String): BaseResult<UserProfileModel>
}