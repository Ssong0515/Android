package com.example.myapplication02

// p.128

// 일반함수
fun fun1(num11: Int, num22: Int) :Int {
    var result: Int = 0
    result = num11 + num22
    return result
}

// 람다함수(익명함수), 변수에 함수를 저장
var result2 = { num1: Int, num2: Int ->
    var result: Int = 0
    result = num1 + num2
    result
}

// 변수에 람다함수 저장
var result22: (Int, Int) -> Int = {
    num1: Int, num2: Int ->
    var result = 0
    result = num1 + num2
    result
}

// 매개변수가 1개(Int)를 가지며 그 값을 *2 해서 리턴
var result33: (Int) -> Int = {
    num1: Int ->
    var result = 0
    result = num1 * 2
    result
}

// 매개변수 생략 가능(자동 추론)
var result44 = { num1: Int -> num1 * 2 }

// 매개변수가 1개 일때 it으로 표현 가능
var result55: (Int) -> Int = {it * 2}

var multi_total: (Int, Int) -> Int = {
    num1: Int, num2: Int ->
    num1 * num2
}

var multi_skin = {
    num1: Int, num2: Int -> num1 * num2
}

var multi_args: (Int, Int) -> Int = {
    num1, num2 -> num1 * num2
}

//var multi = {num1: Int, num2: Int -> num1 * num2}
//var multi: (Int, Int) -> Int = {num1, num2 -> num1 * num2}
//typealias multipleC = (Int, Int) -> Int
//var multi: multipleC = {num1, num2 -> num1 * num2}
var multi = {num1: Int, num2: Int -> "num1 * num2 = ${num1 * num2}" }



fun main(){
    var result = multi_total(10, 20)
    println("multi_total result : $result")
    result = multi_skin(10, 20)
    println("multi_skin result : $result")
    result = multi_args(10, 20)
    println("multi_args result : $result")

    println(multi(100, 200))

    // 매개변수, 반환형 없음
    val greet: () -> Unit = { println("매개변수, 반환형 없음") }
    greet()
    // 둘 다 없는 경우 생략 가능
    val greet1 = { println("매개변수, 반환형 없음") }
    greet1()

    val onep1: (String) -> Unit = {
        println("onep1 : " + it)
        println("onep1 : $it")
    }
//    val onep1 = {
//        s: String ->
//        println("onep1 : $s")
//    }
//    val onep1 = { s: String -> println("onep1 : " + s)}
    onep1("hello world")
}