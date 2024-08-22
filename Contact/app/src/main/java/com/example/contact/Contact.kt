package com.example.contact

import java.io.Serializable


data class Contact(val id: Int, var name: String, var phone: String): Serializable
