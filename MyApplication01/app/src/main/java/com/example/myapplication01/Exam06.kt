package com.example.myapplication01

// p.99 조건문 반복문
fun main() {
    var data = 10
    if (data > 0) {
        println("크다")
    }


    var result = if (data > 0) {
        println("data > 10")
        true
    } else {
        println("data <= 10")
        false
    }
    println("result: $result")

    when(data) {
        10 -> println("data=10")
        else -> println("data")
    }

    var str = "hello"
    when(str) {
        "hello" -> println("data is hello")
        "world" -> println("data is world")
        else -> {
            println("data")
        }
    }

    // break가 필요 없음
    var data102: Any = 10
    when(data102) {
        is String -> println("data102 is String")
        20, 30 -> println("data102 is 20 or 30")
        in 1..10 -> println("data102 is 1..10")
        else -> println("data102...")
    }

    var data103 = 10
    var result103 = when {
        data103 <= 0 -> "data is <= 0"
        data103 > 100 -> "data is > 100"
        else -> "data"
    }

    println("result103: $result103")


    // 반복문
    var sum1 = 0
    for (i in 1..10) {
        sum1 += i
        println("i: $i, sum1: $sum1")
    }

    // until: 1씩 증가하나 마지막은 포함하지 않음
    for (i in 1 until 10) {
        println("i: $i")
    }

    // step: 증감 값 설정
    for (i in 1..10 step 2){
        println("i: $i")
    }

    // downTo: 1씩 감소
    for (i in 10 downTo 1) {
        println("i: $i")
    }

    for (i in 10 downTo 1 step 3) {
        println("i: $i")
    }

    var data104 = arrayOf(10, 20, 30)

    for (i in data104) {
        println(i)
    }

    // indices : index값 출력
    for(i in data104.indices) {
        print(i)
        if (i != data104.size-1) print(",")
    }

    println()

    // withIndex(): data와 index 같이 출력
    var arr = arrayOf(10, 20, 30, 40, 50)
    for ((index, value) in arr.withIndex()) {
        println("value: $value, index: $index")
    }

    // while
    var x = 1
    sum1 = 0
    while (x < 11) {
        sum1 += x
        println("x : $x, sum1: $sum1")
        x++
    }

}