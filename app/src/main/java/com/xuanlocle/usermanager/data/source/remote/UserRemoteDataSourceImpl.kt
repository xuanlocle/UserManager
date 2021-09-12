package com.xuanlocle.usermanager.data.source.remote

import com.xuanlocle.usermanager.data.model.UserProfileRemoteModel
import com.xuanlocle.usermanager.data.model.UserRemoteModel
import com.xuanlocle.usermanager.data.source.remote.api.UserService
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl(
    private val service: UserService
) : UserRemoteDataSource {


    override suspend fun fetchUsers(sinceUserId: Int): BaseResult<List<UserRemoteModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                service.fetchUsersAsync(sinceUserId)
                    .await()
                    .let { reposRes ->
                        BaseResult.Success(reposRes)
                    }

            } catch (ex: Exception) {
                BaseResult.Error(ex.message.toString())
            }
        }
    }

    override suspend fun fetchUserProfile(userLoginId: String): BaseResult<UserProfileRemoteModel> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                service.fetchUserProfileAsync(userLoginId)
                    .await()
                    .let { reposRes ->
                        BaseResult.Success(reposRes)
                    }

            } catch (ex: Exception) {
                BaseResult.Error(ex.message.toString())
            }
        }
    }
}