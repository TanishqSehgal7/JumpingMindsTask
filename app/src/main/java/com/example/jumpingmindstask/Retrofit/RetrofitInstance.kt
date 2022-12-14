package com.example.jumpingmindstask.Retrofit

import com.example.jumpingmindstask.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getRetrofitInstance(): TvShowApiInterface {

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TvShowApiInterface::class.java)
    }
}