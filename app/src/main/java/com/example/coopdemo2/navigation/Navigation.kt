package com.example.coopdemo2.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coopdemo2.R
import com.example.coopdemo2.destinations.Destination

@Composable
fun BottomNavBar(navController: NavController) {
    // navBackStackEntry gives us info about the current screen
    // navController always knows which destination is active because of the back stack
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val ic_random = painterResource(id = R.drawable.outline_search_24)
    val ic_petpaw = painterResource(id = R.drawable.outline_pets_24)

    // horizontal bar at the bottom of the screen
    NavigationBar {
        listOf(
            Destination.RandomCatScreen to ic_petpaw,
            Destination.CatBreedScreen to ic_random
        ).forEach { (destination, iconPainter) ->
            NavigationBarItem(
                selected = currentDestination == destination.route,

                // navController.navigate pushes the route onoto the back stack
                onClick = { navController.navigate(destination.route) },
                icon = { Icon(painter = iconPainter, contentDescription = destination.label) },
                label = { Text(destination.label) }
            )
        }
    }
}