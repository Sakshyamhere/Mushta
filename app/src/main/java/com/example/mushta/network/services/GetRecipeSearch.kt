package com.example.mushta.network.services

import com.example.mushta.data.API_KEY
import com.example.mushta.model.search.SearchRecipe
import retrofit2.http.GET
import retrofit2.http.Query

interface GetRecipeSearch {
    @GET("recipes/complexSearch?apiKey=$API_KEY&number=30")
    suspend fun getRecipeSearch(@Query("query") query: String) : SearchRecipe
}