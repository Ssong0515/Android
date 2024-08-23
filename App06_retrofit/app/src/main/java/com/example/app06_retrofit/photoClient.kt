package com.example.app06_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object photoClient {
    val retrofit: PhotoInterface = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PhotoInterface::class.java)
}