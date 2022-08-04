package com.example.jumpingmindstask.Model


import com.google.gson.annotations.SerializedName

data class TvShowsModelClassItem(
    @SerializedName("averageRuntime")
    val averageRuntime: Int?,
    @SerializedName("ended")
    val ended: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("officialSite")
    val officialSite: String?,
    @SerializedName("premiered")
    val premiered: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated")
    val updated: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("weight")
    val weight: Int?
)