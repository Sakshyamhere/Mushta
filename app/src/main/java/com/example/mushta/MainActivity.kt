package com.example.mushta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mushta.screens.HomeScreen
import com.example.mushta.screens.Recipe
import com.example.mushta.screens.Search
import com.example.mushta.ui.theme.MushtaTheme
import com.example.mushta.viewmodel.RecipeUiState
import com.example.mushta.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MushtaTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column {
                    }
                    Application(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Application(navController: NavHostController,modifier: Modifier) {
    val recipeViewModel : RecipeViewModel = viewModel()
    Surface(
        modifier = modifier
    ) {
        RecipeAppNavigation(navController = navController , recipeViewModel.recipeUiState )
    }
}


@Composable
fun RecipeAppNavigation(navController: NavHostController , recipeUiState: RecipeUiState) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController,recipeUiState)
        }
        composable(
            route = "recipe/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
           Recipe(id = id)
        }
        composable(
            route = "search/{query}",
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: return@composable
            Search(query = query, navController)
        }
    }
}