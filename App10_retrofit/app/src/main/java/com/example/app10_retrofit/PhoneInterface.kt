package com.example.app10_retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PhoneInterface {
    // 전체보기
    @GET("list")
    fun findAll(): Call<List<Phone>>

    // 추가
    @POST("insert")
    fun save(@Body phone: Phone): Call<Phone>

    // 수정
    @PUT("update/{id}")
    fun update(@Path("id") id: Long, @Body phone: Phone): Call<Phone>

    // 삭제
    @DELETE("delete/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>
}
