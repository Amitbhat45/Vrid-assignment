package com.example.vrid_assignment.UiLayer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vrid_assignment.Utilities.ResponseState
import com.example.vrid_assignment.Viewmodel.BlogViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: BlogViewModel,navController: NavController) {
    Scaffold(
        containerColor = Color(0xFF000000),
        topBar = { Topappbar() }
    ) {
        val blogs by viewModel.blogs.collectAsState()
        val listState = rememberLazyListState()


        LaunchedEffect(listState.firstVisibleItemIndex) {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val dataSize = (blogs as? ResponseState.Success)?.data?.size ?: 0
            if (lastVisibleItemIndex == dataSize - 1) {
                viewModel.GetBlogs()
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (val state = blogs) {
                is ResponseState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                is ResponseState.Success -> {
                    state.data.forEach { blog ->
                        item {
                            BlogCard(blog,navController)
                        }
                    }
                }
                is ResponseState.Error -> {
                    item {
                        Text(
                            text = "Error: ${state.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun Topappbar() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 20.dp, end = 40.dp),
    ) {

        Text(text = "BlogFeed",color = Color(0xFFffffff),
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.W500)
        Spacer(modifier = Modifier.height(8.dp))
        CustomHeightDivider()
    }
}
@Composable
fun CustomHeightDivider() {
    Box(
        modifier = Modifier
            .height(3.dp)
            .width(60.dp)
            .background(color = Color(0xFFFF6B00))
    )
}
