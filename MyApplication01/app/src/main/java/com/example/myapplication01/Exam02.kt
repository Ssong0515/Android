package com.example.myapplication01

fun main() {
    function1()         // 매개변수 없는 함수
    function2(10,20)    // num1 : 10 , num2 : 20
                        // 합계 : 30
    var hap = function3(50,70)
    println("리턴된 hap : $hap")
    add(10,20)  // 결과 : 30
    var subValue = sub() // 결과 10
    println("뺄셈 결과 : $subValue")
    var mulValue = multiply(10,20)  // 결과 200
    divide() // 결과 2

}
fun function1() {
    println("매개변수 없는 함수.")
}
fun function2(num1:Int,num2:Int) {
    val sum = num1+num2
    println("num1 : $num1, num2 $num2")
    println("합계 : $sum")
//    println(num1 + num2)
}
fun function3(num3:Int, num4:Int) :Int{

    return  num3 + num4
}
fun add(a:Int, b:Int) {
    println("결과 : ${a+b}")
}
fun sub() :Int {
//    val num1 = 10
//    val num2 = 20
//    println("결과 : ${num2 - num1}")
    val num1 = 20
    val num2 = 10
    return num1 - num2
}
fun multiply(a:Int, b:Int) {
    println("결과 : ${a * b}")
}
fun divide() {
    val num1 = 10
    val num2 = 20
    println("결과 : ${num2 / num1}")
}