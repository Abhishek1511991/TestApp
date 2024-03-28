package com.example.testapi.api

import com.example.testapi.model.Hero
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("marvel")
    suspend fun getHeroes(): Response<List<Hero>>
}