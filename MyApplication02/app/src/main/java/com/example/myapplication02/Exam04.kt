package com.example.myapplication02

// p.135 널 안정성 (null safe)
fun main(){
    var data: String? = null
    val length = if(data == null) {
        0
    } else {
        data.length
    }
    println("data length: ${length}")
    var data2: String? = null
    println("data2 length: ${data2?.length ?: 0}")

}