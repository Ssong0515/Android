package com.example.myapplication01

// p.107 클래스와 생성자
// 클래서 선언
class User01{}
// 빈클래스의 경우는 {} 생략 가능
class User02

class User107 {
    var name = "ssong"
    constructor(name: String) {
        this.name = name
    }

    fun someFun(){
        println("User107의 name: $name")
    }

    class SomeClass{}
}

fun main(){
    val user107 = User107("kim")
    user107.someFun()

    val user108 = User108("ssong", 10)
    user108.someFun()

    val user109 = User109("song109", 100)
    user109.someFun()

    val user112 = User112("song112")
    val user1122 = User112("song112", 100)

    val user113 = User113("song113", 113)
    
}

// 주 생성자 사용 constructor
class User03 constructor(){}

// 주 생성자의 constructor 생략 가능
class User108(name: String, count: Int) {

    var name = "nope"
    var count = 0

    init {
        this.name = name
        this.count = count
        println("User108의 주 생성자 호출")
    }

    fun someFun() {
        println("name: $name, count: $count")
    }
}

// var val 키워드를 생성자에 사용 시, 클래스의 멤버변수처럼 사용 가능
class User109(val name: String, var count: Int) {
    fun someFun(){
        println("name: $name, count: $count")
    }
}

// p.112 보조생성자  ==> 클래스 본문에 constructor 키워드로 선언한 함수

class User112 {
    constructor(name: String) {
        println("생성자 name 하나 호출")
    }

    constructor(name: String, count: Int) {
        println("생성자 name, count 두개 호출")
    }
}

// 주생성자 + 보조생성자 => 보조생성자는 주 생성자를 반드시 호출 해야 함
class User113(name: String) {

    init {
        println("User113 name 있는 주 생성자")    // 1번 실행
    }
    
    // 보조생성자 -> :this() 주 생성자 호출
    constructor(name: String, count: Int) :this(name) {
        println("매개변수가 2개인 보조 생성자")     // 2번 실행
    }
}