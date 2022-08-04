package com.example.jumpingmindstask.Repository

import com.example.jumpingmindstask.Retrofit.TvShowApiInterface

class TvShowsRepository (private val tvShowApiInterface: TvShowApiInterface) {

    suspend fun getTvShows() = tvShowApiInterface.getTvShowResponse()
}