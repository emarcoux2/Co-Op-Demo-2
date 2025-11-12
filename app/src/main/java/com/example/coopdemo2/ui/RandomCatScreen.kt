package com.example.coopdemo2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.coopdemo2.viewmodel.CatViewModel

@Composable
fun RandomCatScreen(catViewModel: CatViewModel = viewModel()) {
    val imageUrl by catViewModel.randomCatPicture.collectAsState()
    val isLoading by catViewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { catViewModel.loadRandomCat() }) {
                Text("Load A Random Kitty! 🐈")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Loading...")
        } else {
            imageUrl?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = "Random Cat",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}