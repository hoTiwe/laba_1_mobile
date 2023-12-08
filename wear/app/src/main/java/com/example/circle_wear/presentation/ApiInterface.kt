package com.example.circle_wear.presentation

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("recipes2022.json")
    suspend fun getRecipes(): Response<List<Recipe>>
}