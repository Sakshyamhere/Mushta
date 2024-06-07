package com.example.mushta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushta.model.random.RecipeResponse
import com.example.mushta.network.repo.RecipeApi
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface RecipeUiState {
    data class Success(val recipe : RecipeResponse) : RecipeUiState
    data object Loading : RecipeUiState
    data object Error : RecipeUiState
}

class RecipeViewModel : ViewModel() {
    var recipeUiState : RecipeUiState by mutableStateOf(RecipeUiState.Loading)
    init {
        getRecipe()
    }
    private fun getRecipe() {
        viewModelScope.launch {
        recipeUiState = try {
           val listResult = RecipeApi().retrofitService.getRecipe()
            RecipeUiState.Success(listResult)
        }catch (e : IOException) {
            RecipeUiState.Error
        }
        catch (e: HttpException) {
            RecipeUiState.Error
        }
        }
    }
}