package com.example.mushta.network.repo

import com.example.mushta.network.builder.retrofit
import com.example.mushta.network.services.RecipeApiService

class RecipeApi {
val retrofitService : RecipeApiService by lazy {
    retrofit.create(RecipeApiService::class.java)
}
}