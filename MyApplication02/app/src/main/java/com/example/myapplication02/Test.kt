package com.example.myapplication02

open class PTest(){
    open var pvalue01 = 10
    var pval =1
    open fun fun1(){
        println("PTest fun1")
    }
}

class CTest(val name: String) : PTest(){
    override var pvalue01 =100
    override fun fun1(){
        println("override fun1")
    }
}

fun main(){
    val p1:PTest = PTest()
    val c1:PTest = CTest("홍길동")
    p1.fun1() // override fun1
    c1.fun1() // override fun1
    println("pvalue01 : ${c1.pvalue01}") // pvalue : 100
    println("pval: ${p1.pval}")
}