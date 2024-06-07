package com.example.mushta.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushta.model.recipewithid.Recipe
import com.example.mushta.network.repo.GetRecipeWithIdApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface GetRecipeWithIdUiState {
    data class Success(val recipe : Recipe) : GetRecipeWithIdUiState
    data object Loading : GetRecipeWithIdUiState
    data object Error : GetRecipeWithIdUiState
}

class RecipeWithIdViewModel : ViewModel() {
    var recipeWithIdUiState : GetRecipeWithIdUiState by mutableStateOf(GetRecipeWithIdUiState.Loading)
    fun getRecipeWithIdViewModel(id: String) {
        Log.d("getRecipeWithIdViewModel", "getRecipeWithIdViewModel: $id ")
        viewModelScope.launch {
            recipeWithIdUiState = try {
                val listResult = GetRecipeWithIdApi().retrofitService.getRecipeWithId(id)
                GetRecipeWithIdUiState.Success(listResult)
            }catch (e : IOException) {
                GetRecipeWithIdUiState.Error
            }
        }
    }
}