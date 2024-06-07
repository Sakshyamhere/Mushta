package com.example.mushta.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mushta.model.search.Result
import com.example.mushta.model.search.SearchRecipe
import com.example.mushta.viewmodel.GetRecipeWithSearchUiState
import com.example.mushta.viewmodel.SearchViewModel

@Composable
fun Search(query: String, navController: NavController) {
    val searchViewModel: SearchViewModel = viewModel()

    LaunchedEffect(query) {
        searchViewModel.getRecipeWithSearch(query)
    }

    when (val recipeWithSearchUiState = searchViewModel.getRecipeWithSearchUiState) {
        is GetRecipeWithSearchUiState.Success -> DisplaySearchResults(recipeWithSearchUiState.recipe, navController)
        GetRecipeWithSearchUiState.Error -> ErrorSearchScreen()
        GetRecipeWithSearchUiState.Loading -> LoadingSearchScreen()
        else -> {}
    }
}

@Composable
fun DisplaySearchResults(recipe: SearchRecipe, navController: NavController) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(recipe.results) { result ->
            RecipeItemSearch(result, navController)
        }
    }
}

@Composable
fun RecipeItemSearch(result: Result, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                navController.navigate("recipe/${result.id}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        ) {
            AsyncImage(
                model = result.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
            result.title.let { title ->
                Text(
                    text = if (title.length < 10) title else "${title.take(10)}...",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 17.sp,
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}

@Composable
fun ErrorSearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error",
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun LoadingSearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
