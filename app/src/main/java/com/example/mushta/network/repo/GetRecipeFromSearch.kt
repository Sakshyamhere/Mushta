package com.example.mushta.network.repo

import com.example.mushta.network.builder.retrofit
import com.example.mushta.network.services.GetRecipeSearch

class GetRecipeFromSearch {
    val retrofitService : GetRecipeSearch by lazy {
        retrofit.create(GetRecipeSearch::class.java)
    }
}