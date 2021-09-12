package com.xuanlocle.usermanager.data.model

import com.google.gson.annotations.SerializedName

data class UserProfileRemoteModel(
    @SerializedName( "id")
    val id: Int,

    @SerializedName( "login")
    val login: String,

    @SerializedName( "avatar_url")
    val avatarURL: String,

    @SerializedName( "html_url")
    val htmlURL: String,

    val name: String?,
    val location: String?,
    val bio: String? = null,

    val followers: Long,
    val following: Long,

    @SerializedName( "public_repos")
    val repos: Long,
)