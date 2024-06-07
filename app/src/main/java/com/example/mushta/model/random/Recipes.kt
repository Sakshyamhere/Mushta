package com.example.mushta.model.random

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val recipes: List<Recipe>
)

@Serializable
data class Recipe(
    val vegetarian: Boolean? = null,
    val vegan: Boolean? = null,
    val glutenFree: Boolean? = null,
    val dairyFree: Boolean? = null,
    val veryHealthy: Boolean? = null,
    val cheap: Boolean? = null,
    val veryPopular: Boolean? = null,
    val sustainable: Boolean? = null,
    val lowFodmap: Boolean? = null,
    val weightWatcherSmartPoints: Int? = null,
    val gaps: String? = null,
    val preparationMinutes: Int? = null,
    val cookingMinutes: Int? = null,
    val aggregateLikes: Int? = null,
    val healthScore: Int? = null,
    val creditsText: String? = null,
    val sourceName: String? = null,
    val pricePerServing: Double? = null,
    val extendedIngredients: List<ExtendedIngredient>? = null,
    val id: Int? = null,
    val title: String? = null,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val sourceUrl: String? = null,
    val image: String? = null,
    val imageType: String? = null,
    val summary: String? = null,
    val cuisines: List<String>? = null,
    val dishTypes: List<String>? = null,
    val diets: List<String>? = null,
    val occasions: List<String>? = null,
    val instructions: String? = null,
    val analyzedInstructions: List<AnalyzedInstruction>? = null,
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null
)

@Serializable
data class ExtendedIngredient(
    val id: Int? = null,
    val aisle: String? = null,
    val image: String? = null,
    val consistency: String? = null,
    val name: String? = null,
    val nameClean: String? = null,
    val original: String? = null,
    val originalName: String? = null,
    val amount: Double? = null,
    val unit: String? = null,
    val meta: List<String>? = null,
    val measures: Measures? = null,
    val localizedName: String? = null // Make this field nullable
)

@Serializable
data class Measures(
    val us: Measure? = null,
    val metric: Measure? = null
)

@Serializable
data class Measure(
    val amount: Double? = null,
    val unitShort: String? = null,
    val unitLong: String? = null
)

@Serializable
data class AnalyzedInstruction(
    val name: String? = null,
    val steps: List<Step>? = null
)

@Serializable
data class Step(
    val number: Int? = null,
    val step: String? = null,
    val ingredients: List<Ingredient>? = null,
    val equipment: List<Equipment>? = null,
    val length: Length? = null
)

@Serializable
data class Ingredient(
    val id: Int? = null,
    val name: String? = null,
    val localizedName: String? = null,
    val image: String? = null
)

@Serializable
data class Equipment(
    val id: Int? = null,
    val name: String? = null,
    val localizedName: String? = null,
    val image: String? = null
)

@Serializable
data class Length(
    val number: Int? = null,
    val unit: String? = null
)
