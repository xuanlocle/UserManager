package com.xuanlocle.usermanager.mapper

import com.xuanlocle.usermanager.data.model.UserProfileModel
import com.xuanlocle.usermanager.data.source.local.entity.DBUserProfileEntity

class UserProfileMapperLocal : BaseMapper<DBUserProfileEntity, UserProfileModel> {
    override fun transformToDomain(user: DBUserProfileEntity): UserProfileModel {
        return UserProfileModel(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
            name = user.name,
            location = user.location,
            bio = user.bio,
            followers = user.followers,
            following = user.following,
            repos = user.repos,
        )
    }

    override fun transformToDTO(user: UserProfileModel): DBUserProfileEntity {
        return DBUserProfileEntity(
            login = user.login,
            id = user.id,
            avatarURL = user.avatarURL,
            htmlURL = user.htmlURL,
            name = user.name,
            location = user.location,
            bio = user.bio,
            followers = user.followers,
            following = user.following,
            repos = user.repos,
        )
    }
}