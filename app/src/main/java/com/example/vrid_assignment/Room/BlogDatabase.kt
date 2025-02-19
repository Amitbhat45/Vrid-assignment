package com.example.vrid_assignment.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BlogEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        @Volatile
        private var INSTANCE: BlogDatabase? = null

        fun GetDatabase(context: Context): BlogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlogDatabase::class.java,
                    "blog_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}