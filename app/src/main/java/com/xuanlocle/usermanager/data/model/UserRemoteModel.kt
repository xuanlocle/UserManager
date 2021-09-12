package com.xuanlocle.usermanager.data.model

import com.google.gson.annotations.SerializedName

data class UserRemoteModel(
    val login: String,
    val id: Int,

    @SerializedName("avatar_url")
    val avatarURL: String,

    @SerializedName("html_url")
    val htmlURL: String,
)