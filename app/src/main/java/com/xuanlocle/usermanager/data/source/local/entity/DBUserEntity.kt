package com.xuanlocle.usermanager.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class DBUserEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey()
    val id: Int,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "node_id")
    val nodeID: String,

    @ColumnInfo(name = "avatar_url")
    val avatarURL: String,

    @ColumnInfo(name = "gravatar_id")
    val gravatarID: String,

    val url: String,

    @ColumnInfo(name = "html_url")
    val htmlURL: String,

    @ColumnInfo(name = "followers_url")
    val followersURL: String,

    @ColumnInfo(name = "following_url")
    val followingURL: String,

    @ColumnInfo(name = "gists_url")
    val gistsURL: String,

    @ColumnInfo(name = "starred_url")
    val starredURL: String,

    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsURL: String,

    @ColumnInfo(name = "organizations_url")
    val organizationsURL: String,

    @ColumnInfo(name = "repos_url")
    val reposURL: String,

    @ColumnInfo(name = "events_url")
    val eventsURL: String,

    @ColumnInfo(name = "received_events_url")
    val receivedEventsURL: String,

    val type: String,

    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean

)