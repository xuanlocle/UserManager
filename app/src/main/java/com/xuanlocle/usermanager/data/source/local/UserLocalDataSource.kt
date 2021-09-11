package com.xuanlocle.usermanager.data.source.local

import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity

interface UserLocalDataSource {
    suspend fun fetchUsers(sinceId: Int = 0): List<DBUserEntity>

    suspend fun saveUsersToLocal(users: List<DBUserEntity>)

    suspend fun checkExistUsers(userId: Int = 0): Boolean

    suspend fun removeOldData()
}