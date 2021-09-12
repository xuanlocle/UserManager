package com.xuanlocle.usermanager.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_table")
data class DBUserProfileEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey()
    val id: Int,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    val avatarURL: String,

    @ColumnInfo(name = "html_url")
    val htmlURL: String,

    val name: String?,
    val location: String?,
    val bio: String?,

    val followers: Long,
    val following: Long,

    @ColumnInfo( name = "public_repos")
    val repos: Long,
)