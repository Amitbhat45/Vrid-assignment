package com.example.vrid_assignment.UiLayer

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.vrid_assignment.Model.BlogPost
import com.example.vrid_assignment.Room.BlogEntity
import org.apache.commons.lang3.StringEscapeUtils
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BlogCard(blogDetails:BlogEntity,
             navController: NavController){
    val encodedUrl = URLEncoder.encode(blogDetails.url, StandardCharsets.UTF_8.toString())
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
        onClick = {navController.navigate("webview/${encodedUrl}")}

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
                            text = "${removeHtmlTagsAndDecodeEntities(blogDetails.title)}",
                            color = Color(0xFFffffff),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(fontSize = 20.sp),
                            fontWeight = FontWeight.W700
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${formatDate(blogDetails.date)}",
                            color = Color(0xFFFF6B00),
                            style = TextStyle(fontSize = 14.sp),
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${removeHtmlTagsAndDecodeEntities(blogDetails.excerpt)}",
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



fun removeHtmlTagsAndDecodeEntities(input: String): String {
    // Remove HTML tags
    val withoutHtmlTags = input.replace(Regex("<.*?>"), "")
    // Decode HTML entities
    return StringEscapeUtils.unescapeHtml4(withoutHtmlTags)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(inputDate: String): String {
    return try {
        // Define the formatter for the input date format
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME

        // Parse the input string to a LocalDateTime object
        val parsedDate = LocalDateTime.parse(inputDate, inputFormatter)

        // Define the formatter for the desired output format
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)

        // Format the parsed date to the desired format
        parsedDate.format(outputFormatter)
    } catch (e: DateTimeParseException) {
        "Invalid date format"
    }
}


