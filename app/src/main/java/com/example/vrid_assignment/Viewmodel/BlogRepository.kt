package com.example.vrid_assignment.Viewmodel

import com.example.vrid_assignment.Retrofit.BlogService
import com.example.vrid_assignment.Room.BlogDao
import com.example.vrid_assignment.Room.BlogEntity
import com.example.vrid_assignment.Utilities.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class BlogRepository(private val blogService: BlogService, private val blogDao: BlogDao
) {
    private var currentPage = 1

    suspend fun GetBlogs(): Flow<ResponseState<List<BlogEntity>>> = flow {
        emit(ResponseState.Loading)
        try {
            val blogs = blogService.GetBlogs(currentPage, 10)

            val blogEntities = blogs.map { blog ->
                BlogEntity(
                    id = blog.id,
                    title = blog.title.rendered,
                    excerpt = blog.excerpt.rendered,
                    date = blog.date,
                    imgUrl = blog.jetpack_featured_media_url,
                    url = blog.guid.rendered
                )
            }

            blogDao.InsertBlogs(blogEntities)
            currentPage++
            val data = blogDao.GetBlogs().first()
            emit(ResponseState.Success(data))

        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "unexpected error"))
        }

    }
}