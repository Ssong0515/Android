package com.example.app11_member

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class Member(val id: Long, val name: String, val phone: String, val email: String)

object MemberClient {
    val retrofit: MemberInterface = Retrofit.Builder()
        .baseUrl("http://10.100.105.203:8811/member/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MemberInterface::class.java)
}

interface MemberInterface {

    @GET("list")
    fun findAll(): Call<List<Member>>

    @POST("insert")
    fun save(@Body member: Member): Call<Member>

    @PUT("update/{id}")
    fun update(@Path("id") id: Long, @Body member: Member): Call<Member>

    @DELETE("delete/{id}")
    fun deleteById(@Path("id") id: Long): Call<Void>

}