package com.example.jumpingmindstask.Retrofit

import com.example.jumpingmindstask.Model.TvShowsModelClass
import retrofit2.Response
import retrofit2.http.GET

interface TvShowApiInterface {

    @GET("shows")
    suspend fun getTvShowResponse() : Response<TvShowsModelClass>
}