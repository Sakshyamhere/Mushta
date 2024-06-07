package com.example.mushta.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mushta.model.random.Recipe
import com.example.mushta.model.random.RecipeResponse
import com.example.mushta.model.search.Result
import com.example.mushta.viewmodel.RecipeUiState


@Composable
fun HomeScreen(navController : NavHostController, recipeUiState: RecipeUiState) {
    when (recipeUiState) {
        is RecipeUiState.Success -> ReturnScreen(recipeUiState.recipe , navController)
        RecipeUiState.Error -> ErrorScreen()
        RecipeUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun ReturnScreen(recipe: RecipeResponse , navController: NavHostController) {
    val recipes = recipe.recipes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Recipes",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        SearchRecipe(navController)
        ChooseBar()
        if (recipes.isNotEmpty())
        RecipesGrid(recipes = recipes , navController)
    }
}

@Composable
fun RecipesGrid(recipes: List<Recipe> ,navController: NavHostController) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp // Specify the vertical item spacing if needed
    ) {
            items(recipes) { recipe ->
                RecipeItem(recipe = recipe,navController)
            }

    }
}



@Composable
fun RecipeItem(recipe: Recipe, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                navController.navigate("recipe/${recipe.id}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
        ) {
            if (recipe.image != null) {
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
        }
            else {
            AsyncImage(
                model = "https://img.spoonacular.com/recipes/651767-556x370.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
        }

            recipe.title?.let {
                Text(
                    text = if (it.length < 10) it else "${it.take(10)}...",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 17.sp,
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )
            } ?: run {
                Text(
                    text = "Random recipe",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 17.sp,
                    style = TextStyle(fontWeight = FontWeight.SemiBold)
                )
            }
            recipe.dishTypes?.get(0)?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(10.dp,0.dp),
                    fontSize = 10.sp,
                    color = Color.Gray,
                    style = TextStyle(fontWeight = FontWeight.Normal)
                )
            } ?: run {
                Text(
                    text = "UCanCook",
                    modifier = Modifier.padding(10.dp,0.dp),
                    fontSize = 10.sp,
                    color = Color.Gray,
                    style = TextStyle(fontWeight = FontWeight.Normal)
                )
            }
        }
    }
}

@Composable
fun SearchRecipe(navController: NavHostController) {
    var search by remember { mutableStateOf("") }
    OutlinedTextField(
        value = search,
        onValueChange = { search = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 20.dp),
        shape = RoundedCornerShape(70),
        placeholder = {
            Text(text = "Search recipes")
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (search.isNotEmpty()) {
                    navController.navigate("search/$search")
                }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(70))
                    .background(Color.Yellow)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
        },
    )
}

@Composable
fun ErrorScreen() {
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
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ChooseBar() {
    // Implementation of ChooseBar
}
