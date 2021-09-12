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
            nodeID = user.nodeID,
            avatarURL = user.avatarURL,
            gravatarID = user.gravatarID,
            url = user.url,
            htmlURL = user.htmlURL,
            followersURL = user.followersURL,
            followingURL = user.followingURL,
            gistsURL = user.gistsURL,
            starredURL = user.starredURL,
            subscriptionsURL = user.subscriptionsURL,
            organizationsURL = user.organizationsURL,
            reposURL = user.reposURL,
            eventsURL = user.eventsURL,
            receivedEventsURL = user.receivedEventsURL,
            type = user.type,
            siteAdmin = user.siteAdmin,
        )
    }

    override fun transformToDTO(user: UserModel): DBUserEntity {
        return DBUserEntity(
            login = user.login,
            id = user.id,
            nodeID = user.nodeID,
            avatarURL = user.avatarURL,
            gravatarID = user.gravatarID,
            url = user.url,
            htmlURL = user.htmlURL,
            followersURL = user.followersURL,
            followingURL = user.followingURL,
            gistsURL = user.gistsURL,
            starredURL = user.starredURL,
            subscriptionsURL = user.subscriptionsURL,
            organizationsURL = user.organizationsURL,
            reposURL = user.reposURL,
            eventsURL = user.eventsURL,
            receivedEventsURL = user.receivedEventsURL,
            type = user.type,
            siteAdmin = user.siteAdmin,
        )
    }
}