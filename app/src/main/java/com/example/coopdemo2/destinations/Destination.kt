package com.example.coopdemo2.destinations

// represents screens or routes in the app
// each destination is defined as a Composable
open class Destination(val route: String, val label: String) {
    object RandomCatScreen: Destination("randomCatScreen", "Random")
    object CatBreedScreen: Destination("catBreedScreen", "Search Breed Pictures")
}