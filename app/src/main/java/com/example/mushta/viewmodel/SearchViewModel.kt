package com.example.mushta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushta.model.search.SearchRecipe
import com.example.mushta.network.repo.GetRecipeFromSearch
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface GetRecipeWithSearchUiState {
    data class Success(val recipe : SearchRecipe) : GetRecipeWithSearchUiState
    data object Loading : GetRecipeWithSearchUiState
    data object Error : GetRecipeWithSearchUiState
}

class SearchViewModel : ViewModel()  {
    var getRecipeWithSearchUiState : GetRecipeWithSearchUiState by mutableStateOf(GetRecipeWithSearchUiState.Loading)
    fun getRecipeWithSearch(query : String) {
        viewModelScope.launch {
            getRecipeWithSearchUiState = try {
                val listResult = GetRecipeFromSearch().retrofitService.getRecipeSearch(query)
                GetRecipeWithSearchUiState.Success(listResult)
            }catch (e : IOException) {
                GetRecipeWithSearchUiState.Error
            }
        }
    }
}