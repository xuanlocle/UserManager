package com.xuanlocle.usermanager.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class DBUserEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey()
    val id: Int,

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    val avatarURL: String,

    @ColumnInfo(name = "html_url")
    val htmlURL: String,
)