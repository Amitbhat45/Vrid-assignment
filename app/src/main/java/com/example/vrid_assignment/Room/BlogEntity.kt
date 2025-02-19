package com.example.vrid_assignment.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogEntity(
    @PrimaryKey val id:Int,
    val title:String,
    val excerpt:String,
    val date:String,
    val imgUrl:String,
    val url: String
)
