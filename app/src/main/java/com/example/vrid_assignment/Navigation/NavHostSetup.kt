package com.example.vrid_assignment.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vrid_assignment.UiLayer.MainScreen
import com.example.vrid_assignment.Viewmodel.BlogViewModel

@Composable
fun NavHostSetup(blogViewModel: BlogViewModel) {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(blogViewModel)
        }

    }
}