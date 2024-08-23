package com.example.app06_retrofit

import com.example.app06_retrofit.photo.Photo
import com.example.app06_retrofit.post.Post
import retrofit2.Call
import retrofit2.http.GET

interface PhotoInterface {
    @GET("photos/")
    fun doGetPhotos(): Call<List<Photo>>

    @GET("posts/")
    fun doGetPosts(): Call<List<Post>>
}