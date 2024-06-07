package com.example.mushta.network.services

import com.example.mushta.data.API_KEY
import com.example.mushta.model.recipewithid.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRecipeWithId {
    @GET("recipes/{id}/information?apiKey=$API_KEY")
    suspend fun getRecipeWithId(@Path("id") id: String) : Recipe
}