package com.example.mushta.model.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchRecipe(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)