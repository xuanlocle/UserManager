package com.xuanlocle.usermanager.mapper

import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity

class UsersMapperLocal : BaseMapper<List<DBUserEntity>, List<UserModel>> {
    override fun transformToDomain(users: List<DBUserEntity>): List<UserModel> {
        val mapper = UserMapperLocal()
        return users.map { user ->
            mapper.transformToDomain(user)
        }
    }

    override fun transformToDTO(users: List<UserModel>): List<DBUserEntity> {
        val mapper = UserMapperLocal()
        return users.map { user ->
            mapper.transformToDTO(user)
        }
    }

}


class UserMapperLocal : BaseMapper<DBUserEntity, UserModel> {
    override fun transformToDomain(user: DBUserEntity): UserModel {
        return UserModel(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
        )
    }

    override fun transformToDTO(user: UserModel): DBUserEntity {
        return DBUserEntity(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
        )
    }
}