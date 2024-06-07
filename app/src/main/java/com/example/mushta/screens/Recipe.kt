package com.example.mushta.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mushta.model.recipewithid.AnalyzedInstruction
import com.example.mushta.model.recipewithid.Recipe
import com.example.mushta.viewmodel.GetRecipeWithIdUiState
import com.example.mushta.viewmodel.RecipeWithIdViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Recipe(id: String) {
  val recipeWithIdViewModel : RecipeWithIdViewModel = viewModel()
    recipeWithIdViewModel.getRecipeWithIdViewModel(id)
  when (val recipeWithIdUiState = recipeWithIdViewModel.recipeWithIdUiState) {
       is GetRecipeWithIdUiState.Success -> RecipeScreen(recipeWithIdUiState.recipe)
       GetRecipeWithIdUiState.Error -> ErrorScreenInRecipe()
       GetRecipeWithIdUiState.Loading -> LoadingScreenInRecipe()
     }
}

@Composable
fun RecipeScreen(recipe: Recipe) {
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    item {
      AsyncImage(
        model = recipe.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
      )
      recipe.title?.let {
        Text(
          text = it,
          textAlign = TextAlign.Center,
          fontSize = 28.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
        )
      }

      recipe.summary?.let { it ->
        val spannedText = HtmlCompat.fromHtml(it, 0).toString()
        var showFullText by remember {
          mutableStateOf(false)
        }
        Text(
          text = if (spannedText.length < 100 || showFullText) spannedText else "${spannedText.take(100)}...",
          textAlign = TextAlign.Justify,
          modifier = Modifier
            .padding(10.dp, 5.dp)
            .clickable {
              showFullText = !showFullText
            }
        )
      }
    }

    recipe.analyzedInstructions?.let { instructions ->
      items(instructions) { analyzedInstruction ->
        Text(
          text = "Steps",
          fontSize = 28.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,10.dp,5.dp)
        )
        Steps(analyzedInstruction)
      }
    }
  }
}

@Composable
fun Steps(instruction: AnalyzedInstruction) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp)
  ) {
    instruction.steps?.forEachIndexed { index, step ->
      Text(
        text = "${step.number}. ${step.step}",
        textAlign = TextAlign.Justify,
        fontSize = 16.sp,
        modifier = Modifier.padding(5.dp, 2.dp)
      )
    }
  }
}


@Composable
fun ErrorScreenInRecipe() {
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
fun LoadingScreenInRecipe() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Loading", fontSize = 18.sp, fontWeight = FontWeight.Bold)
  }
}
