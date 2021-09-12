package com.xuanlocle.usermanager.data.source.remote.api

import com.xuanlocle.usermanager.data.model.UserProfileRemoteModel
import com.xuanlocle.usermanager.data.model.UserRemoteModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users")
    fun fetchUsersAsync(@Query("since") sinceUserId: Int): Deferred<List<UserRemoteModel>>

    @GET("users/{id}")
    fun fetchUserProfileAsync(@Path("id") userLoginId: String): Deferred<UserProfileRemoteModel>
}
