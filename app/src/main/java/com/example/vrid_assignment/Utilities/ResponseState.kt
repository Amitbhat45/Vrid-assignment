package com.example.vrid_assignment.Utilities

sealed class ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val message: String) : ResponseState<T>()
    object Loading : ResponseState<Nothing>()
}