package com.xuanlocle.usermanager.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val login: String,
    val id: Int,

    @SerializedName("avatar_url")
    val avatarURL: String,

    @SerializedName("html_url")
    val htmlURL: String,
): Parcelable