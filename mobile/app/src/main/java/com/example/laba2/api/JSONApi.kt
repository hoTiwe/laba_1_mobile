package com.example.laba2.api

import com.example.laba2.database.entities.RecipeDB
import retrofit2.Call
import retrofit2.http.GET

interface JSONApi {
    @GET("recipes2022.json")
    fun getRecipes(): Call<List<RecipeDB>>
}