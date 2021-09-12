package com.xuanlocle.usermanager.data.source.local

import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity
import com.xuanlocle.usermanager.data.source.local.entity.DBUserProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserLocalDataSourceImpl(private val database: UserDatabase) : UserLocalDataSource {

    override suspend fun fetchUsers(sinceId: Int): List<DBUserEntity> {
        return withContext(Dispatchers.IO) {
            database.getUserDao().getUserSinceId(sinceId)
        }
    }

    override suspend fun saveUsersToLocal(users: List<DBUserEntity>) {
        return withContext(Dispatchers.IO) {
            database.getUserDao().insertUsers(users)
        }
    }

    override suspend fun removeOldData() {
        return withContext(Dispatchers.IO) {
            database.getUserDao().deleteAllRepo()
        }
    }

    override suspend fun checkExistUsers(sinceUserId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            database.getUserDao().checkUsersExists(sinceUserId) > 0
        }
    }

    override suspend fun fetchUserProfile(userLoginId: String): DBUserProfileEntity {
        return withContext(Dispatchers.IO) {
            database.getUserDao().getUserProfileById(userLoginId)
        }
    }

    override suspend fun checkExistUserProfile(userLoginId: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.getUserDao().checkUserProfileExists(userLoginId) > 0
        }
    }

    override suspend fun saveUserProfileToLocal(user: DBUserProfileEntity) {
        return withContext(Dispatchers.IO) {
            database.getUserDao().insertUserProfile(user)
        }
    }

}