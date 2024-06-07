package com.example.mushta.network.repo
import com.example.mushta.network.builder.retrofit
import com.example.mushta.network.services.GetRecipeWithId
class GetRecipeWithIdApi {
    val retrofitService : GetRecipeWithId by lazy {
        retrofit.create(GetRecipeWithId::class.java)
    }
}

