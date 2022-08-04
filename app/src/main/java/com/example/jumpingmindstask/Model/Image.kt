package com.example.jumpingmindstask.Model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("original")
    val original: String?
)