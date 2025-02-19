package com.example.vrid_assignment.Retrofit

import com.example.vrid_assignment.Model.BlogPost

import retrofit2.http.GET
import retrofit2.http.Query

interface BlogService {
    @GET("posts")
    suspend fun GetBlogs(@Query("page") page: Int, @Query("per_page") perPage: Int): List<BlogPost>
}