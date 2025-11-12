package com.example.coopdemo2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.coopdemo2.viewmodel.CatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreen(catViewModel: CatViewModel = viewModel()) {

    // each line is subscribing to a StateFlow from the CatViewModel
    // collectAsState allows the UI to react to data updates automatically
    val query by catViewModel.searchQuery.collectAsState()
    val pictures by catViewModel.breedPictures.collectAsState()
    val isLoading by catViewModel.isLoading.collectAsState()

    // this block runs ONCE when the composable first enters composition.
    // this ensures something like a dropdown or a search list
    // has data when the screen loads.
    // if we called catViewModel.loadCatBreeds() at the top level,
    // it would run on every recomposition, causing redundant API calls
    LaunchedEffect(Unit) {
        catViewModel.loadCatBreeds()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { catViewModel.setSearchQuery(it) },
            label = { Text("Search for a breed of cat! 🐈") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { catViewModel.searchAndLoadBreedPictures(query) }) {
            Text("Meow!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Loading...")
        } else {
            LazyColumn {
                items(pictures) { picture ->
                    // Coil loads each image asynchronously from the URL
                    AsyncImage(
                        model = picture.url,
                        contentDescription = "Cat Picture",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}