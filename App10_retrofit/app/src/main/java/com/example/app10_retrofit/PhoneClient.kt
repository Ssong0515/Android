package com.example.app10_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhoneClient {
    val retrofit: PhoneInterface = Retrofit.Builder()
        .baseUrl("http://10.100.105.203:8899")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PhoneInterface::class.java)
}