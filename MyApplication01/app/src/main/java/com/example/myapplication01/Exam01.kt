package com.example.myapplication01

import java.text.SimpleDateFormat
import java.util.Date
import java.util.SimpleTimeZone

val data1 = 100 // 자바 final val로 선언하면 값이 변하지 않음
var data2 = 200
val data3:Int = 300
//var data5:Int
var data = 10

fun formatData(date: Date) :String { // public String formatDate(Date date)
//    코틀린 객체 생성시 new 연산자 없음
    var sdFormat = SimpleDateFormat("yyyy-MM-dd")
//    println(sdFormat.format(date))
    return sdFormat.format(date)

}
class User{
    var name = "hello"
    fun sayHello() {
        println("sayHello")
    }
}

fun main() {
    User()
    val user = User()
    user.sayHello()
    var date1 = formatData(Date())
    println(date1)
    println("코틀린실행하기01")
//    data1 = 300 오류발생 ==> 값 변경 불가
    data2 = 150
    println("data2 : $data2") // 문자열리터럴
    println("data3 : {$data3}")
//    var data4 // 변수 선언시 타입이 없고 초기값 없음 : 오류발생
    var data4:Int
    data4 = 60
    data = 20
}
