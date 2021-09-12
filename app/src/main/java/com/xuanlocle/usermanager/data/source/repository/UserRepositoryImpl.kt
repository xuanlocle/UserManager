package com.xuanlocle.usermanager.data.source.repository

import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.source.local.UserLocalDataSource
import com.xuanlocle.usermanager.data.source.remote.UserRemoteDataSource
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult
import com.xuanlocle.usermanager.mapper.UserMapperLocal
import com.xuanlocle.usermanager.mapper.UserMapperRemote
import com.xuanlocle.usermanager.usersPreference
import com.xuanlocle.usermanager.util.datetime.DateTimeHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {


    override suspend fun fetchUsers(sinceUserId: Int): BaseResult<List<UserModel>> {
        return withContext(Dispatchers.IO) {
            if (localDataSource.checkExistUsers(sinceUserId)) {
                val mapper = UserMapperLocal()
                val repos = localDataSource.fetchUsers(sinceUserId)
                return@withContext BaseResult.Success(mapper.transformToDomain(repos))
            }

            val mapper = UserMapperRemote()
            val localMapper = UserMapperLocal()
            when (val response = remoteDataSource.fetchUsers(sinceUserId)) {
                is BaseResult.Success -> {
                    if (response.data != null) {
                        if (sinceUserId == 0) {
                            saveLastUpdatedAt()
                        }

                        val userModels = mapper.transformToDomain(response.data)
                        localDataSource.saveUsersToLocal(localMapper.transformToDTO(userModels))
                        return@withContext BaseResult.Success(userModels)
                    } else {
                        return@withContext BaseResult.Success(null)
                    }
                }
                is BaseResult.Error -> {
                    return@withContext BaseResult.Error(response.error)
                }
                is BaseResult.Loading -> return@withContext BaseResult.Loading
            }
        }
    }

    private fun saveLastUpdatedAt() {
        usersPreference.setLastUpdateAt(DateTimeHelper.getCurrentDateTime())
    }

    override suspend fun removeOldData() {
        withContext(Dispatchers.IO) {
            localDataSource.removeOldData()
        }
    }
}