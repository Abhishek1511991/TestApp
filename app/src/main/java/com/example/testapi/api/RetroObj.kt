package com.example.testapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroObj {
    private val BASE_URL = "https://simplifiedcoding.net/demos/"

    fun getInterface(): Api {
        val API : Api by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(Api::class.java)
        }
        return API
    }
}