package com.example.myapplication02

open class Parent {
    open var data = 10
    open fun some(){
        println("parent data : $data")
    }
}

var obj = object : Parent(){
    override var data = 10
    override fun some() {
        super.some()
        println("data : $data")
    }
}

val obj1 = object {
    var data1 = 10
    fun some1(){
        println("data1 : $data1")
    }
}

class MyClass {
    var d = 10
    companion object {      // java에서 static
        var d1 = 10
        fun some2(){

        }
    }
}

fun main(){
//    obj1.data = 20
//    obj1.some1()
    println(MyClass.d1)
}