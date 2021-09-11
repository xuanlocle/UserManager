package com.xuanlocle.usermanager.data.source.local

import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity
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
}