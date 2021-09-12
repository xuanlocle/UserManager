package com.xuanlocle.usermanager.mapper

import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.model.UserRemoteModel

class UsersMapperRemote : BaseMapper<List<UserRemoteModel>, List<UserModel>> {
    override fun transformToDomain(users: List<UserRemoteModel>): List<UserModel> {
        val mapper = UserMapperRemote()
        return users
            .map { user ->
                mapper.transformToDomain(user)
            }
    }

    override fun transformToDTO(users: List<UserModel>): List<UserRemoteModel> {
        val mapper = UserMapperRemote()
        return users.map { user ->
            mapper.transformToDTO(user)
        }
    }
}

class UserMapperRemote : BaseMapper<UserRemoteModel, UserModel> {
    override fun transformToDomain(user: UserRemoteModel): UserModel {
        return UserModel(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
        )
    }

    override fun transformToDTO(user: UserModel): UserRemoteModel {
        return UserRemoteModel(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
        )
    }
}