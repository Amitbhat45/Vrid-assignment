package com.example.vrid_assignment.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vrid_assignment.UiLayer.MainScreen
import com.example.vrid_assignment.UiLayer.WebView
import com.example.vrid_assignment.Viewmodel.BlogViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostSetup(blogViewModel: BlogViewModel) {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(blogViewModel,navController)
        }
        composable("webview/{Url}") { backStackEntry ->
            val Url = backStackEntry.arguments?.getString("Url") ?: ""
            WebView(navController,Url = Url)
        }

    }
}