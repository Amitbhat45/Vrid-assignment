package com.example.vrid_assignment.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val Base_Url="https://blog.vrid.in/wp-json/wp/v2/"

    fun ProvideApi(builder: Retrofit.Builder):BlogService{
        return builder.build()
            .create(BlogService::class.java)
    }

    fun ProvideRetrofit(): Retrofit.Builder{
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())

        return retrofit
    }
}