package com.example.vrid_assignment.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vrid_assignment.Utilities.ResponseState
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertBlogs(blogs: List<BlogEntity>)

    @Query("SELECT * FROM blogs")
    fun GetBlogs(): Flow<List<BlogEntity>>
}