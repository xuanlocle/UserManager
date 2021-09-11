package com.xuanlocle.usermanager.mapper

import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.model.UserRemoteModel

class UserMapperRemote : BaseMapper<List<UserRemoteModel>, List<UserModel>> {
    override fun transformToDomain(users: List<UserRemoteModel>): List<UserModel> {
        return users.map { user ->
            UserModel(
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

    override fun transformToDTO(users: List<UserModel>): List<UserRemoteModel> {
        return users.map { user ->
            UserRemoteModel(
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
}