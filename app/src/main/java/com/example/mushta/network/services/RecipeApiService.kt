package com.example.mushta.network.services

import com.example.mushta.data.API_KEY
import com.example.mushta.model.random.RecipeResponse
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes/random?apiKey=$API_KEY&number=30")
    suspend fun getRecipe() : RecipeResponse
}