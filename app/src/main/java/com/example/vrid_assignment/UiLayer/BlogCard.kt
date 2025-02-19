package com.example.vrid_assignment.UiLayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.vrid_assignment.Model.BlogPost
import com.example.vrid_assignment.Room.BlogEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BlogCard(blogDetails:BlogEntity){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(450.dp)
            .padding(10.dp)
            .height(380.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        //onClick = {navController.navigate("details/${Animedetails.mal_id}")}

    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF111111))
        ) {
                Column(Modifier.fillMaxSize()) {
                    GlideImage(
                        model = "${blogDetails.imgUrl}",
                        contentDescription = "",
                        modifier = Modifier
                            .height(200.dp)
                            .width(450.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 12.dp,
                                    topEnd = 12.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            ),
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "${blogDetails.title}",
                            color = Color(0xFFffffff),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(fontSize = 20.sp),
                            fontWeight = FontWeight.W700
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${blogDetails.date}",
                            color = Color(0xFFFF6B00),
                            style = TextStyle(fontSize = 14.sp),
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${blogDetails.excerpt}",
                            color = Color(0xFFCCCCCC),
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(fontSize = 15.sp),
                            fontWeight = FontWeight.W500
                        )


                    }
                }
        }
    }
}

