package com.example.vrid_assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vrid_assignment.Navigation.NavHostSetup
import com.example.vrid_assignment.Retrofit.BlogService
import com.example.vrid_assignment.Retrofit.RetrofitInstance
import com.example.vrid_assignment.Room.BlogDatabase
import com.example.vrid_assignment.UiLayer.MainScreen
import com.example.vrid_assignment.Utilities.ResponseState
import com.example.vrid_assignment.Viewmodel.BlogRepository
import com.example.vrid_assignment.Viewmodel.BlogViewModel
import com.example.vrid_assignment.ui.theme.VridassignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val retrofit = RetrofitInstance.ProvideRetrofit().build()
            val apiService = retrofit.create(BlogService::class.java)
            val blogDao = BlogDatabase.GetDatabase(this).blogDao()
            val repository = BlogRepository(apiService,blogDao)
            val blogViewModel = BlogViewModel(repository)

            VridassignmentTheme {
                        NavHostSetup(blogViewModel = blogViewModel)

            }
        }
    }
}
