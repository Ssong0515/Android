package com.example.app10_member

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object MemberClient {
    val retrofit: MemberInterface = Retrofit.Builder()
        .baseUrl("http://10.100.105.203:8899")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MemberInterface::class.java)
}

interface MemberInterface {

    @GET("select")
    fun findAll(): Call<List<Member>>

    @POST("insert")
    fun save(@Body member: Member): Call<Member>

    @PUT("update{id}")
    fun update(@Path("id") id: Long, @Body member: Member): Call<Member>

    @DELETE("delete{id}")
    fun delete(@Path("id") id: Long): Call<Void>
}

