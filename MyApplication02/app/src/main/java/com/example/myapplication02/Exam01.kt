package com.example.myapplication02

import android.location.Address

// p.123 데이터클래스
class NonDataClass(var name: String, var email: String, var age: Int)
data class DataClass(val name: String, val email: String, val age: Int) {
    lateinit var address: String
    constructor(name: String, email: String, age: Int, address: String)
                :this(name, email, age) {
                    this.address = address
                }
}

fun main(){
    val non1 = NonDataClass("name", "a@a.com", 10)
    val non2 = NonDataClass("name", "a@a.com", 10)
    val non3 = non2
    non3.name = "name3"
    non3.age = 20
    println("non1: ${non1.name}, ${non1.email}, ${non1.age}")
    println("non2: ${non2.name}, ${non2.email}, ${non2.age}")
    println("non1 equals non2 : ${non1.equals(non2)}") // 일반 클래스는 주소값을 비교하기 때문에 false
    println("non1 equals non2 : ${non1 == non2}") // false
    println("non2 equals non3 : ${non2.equals(non3)}") // 일반 클래스는 주소값을 비교하기 때문에 false
    println("non2 equals non3 : ${non2 == non3}") // false

    val data1 = DataClass("name", "a@a.com", 10, "seoul")
    val data2 = DataClass("name", "a@a.com", 10, "seoul")
    println(data2.toString())
    println("data1: ${data1.name}, ${data1.email}, ${data1.age}, ${data1.address}")
    println("data2: ${data2.name}, ${data2.email}, ${data2.age}, ${data2.address}")
    println("data1 equals data2 : ${data1.equals(data2)}") // 데이터 클래스는 값을 비교하기 때문에 true
    println("data1 equals data2 : ${data1 == data2}") // true

}