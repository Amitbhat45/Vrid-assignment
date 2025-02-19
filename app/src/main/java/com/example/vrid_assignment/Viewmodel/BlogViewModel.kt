package com.example.vrid_assignment.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrid_assignment.Room.BlogEntity
import com.example.vrid_assignment.Utilities.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel(private val blogRepository: BlogRepository):ViewModel() {
    private val _blogs = MutableStateFlow<ResponseState<List<BlogEntity>>>(ResponseState.Loading)
    val blogs: StateFlow<ResponseState<List<BlogEntity>>> get() = _blogs

    init{
        GetBlogs()
    }
    fun GetBlogs(){
        viewModelScope.launch {
            blogRepository.GetBlogs().collect{responseState ->
                _blogs.value=responseState
            }
        }
    }
}