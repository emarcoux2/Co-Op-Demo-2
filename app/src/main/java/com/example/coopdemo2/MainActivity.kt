package com.example.coopdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coopdemo2.ui.theme.CoopDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoopDemo2Theme {
                CatApp()
            }
        }
    }
}

// sources used for this app:
// https://developers.thecatapi.com/view-account/ylX4blBYT9FaoVd6OhvR?report=bOoHBz-8t
// https://developer.android.com/develop/ui/compose/navigation
// https://developer.android.com/reference/kotlin/androidx/navigation/NavController
// https://developer.android.com/codelabs/jetpack-compose-navigation#0
// https://developer.android.com/develop/ui/compose/navigation#composable
// https://developer.android.com/develop/ui/compose/state#stateflow
// https://developer.android.com/develop/ui/compose/state#viewmodel
// https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary#LaunchedEffect(kotlin.coroutines.SuspendFunction1)
// https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/
// https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-state-flow/
// https://kotlinlang.org/docs/returns.html
// https://proandroiddev.com/cheatsheet-for-centering-items-in-jetpack-compose-1e3534415237