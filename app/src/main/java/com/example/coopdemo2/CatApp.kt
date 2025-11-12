package com.example.coopdemo2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coopdemo2.destinations.Destination
import com.example.coopdemo2.navigation.BottomNavBar
import com.example.coopdemo2.ui.CatBreedScreen
import com.example.coopdemo2.ui.RandomCatScreen
import com.example.coopdemo2.viewmodel.CatViewModel

@Composable
fun CatApp() {
    // manages navigation between screens
    // ensures the controller survives recompositions of the composable
    val navController = rememberNavController()

    // scopes the ViewModel to the activity or nav graph
    // so it survives configuration changes
    val catViewModel: CatViewModel = viewModel()

    Scaffold(bottomBar = { BottomNavBar(navController) }) { padding ->
        // container for navigable composables
        NavHost(
            navController = navController,
            startDestination = Destination.RandomCatScreen.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Destination.RandomCatScreen.route) {
                RandomCatScreen(catViewModel)
            }
            composable(Destination.CatBreedScreen.route) {
                CatBreedScreen(catViewModel)
            }
        }
    }
}