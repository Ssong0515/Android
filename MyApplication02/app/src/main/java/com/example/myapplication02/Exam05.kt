package com.example.myapplication02

// 확장함수

data class Person(var name: String, var age: Int)

fun main() {
    val person = Person("", 0)
    person.name = "ann"
    person.age = 10
    println(person)

    // 1. let => not-null 체크 때 유용
//    val nameStr = person?.let { it.name } ?: "Noname"
    val nameStr = person?.name ?: "Noname"
    println("nameStr: $nameStr")

    // 2. with
    with(person) {
        println(name)
        println(age)
    }


    // 3. apply ==> 자기 자신을 반환
    val result = person.apply {
        name = "Android"
        age = 30
    }
    println("apply person $person")
    println("apply person result $result")


}

fun interface InterTest {
    fun fmethod(i: Int): Boolean
}


val isEven = object : InterTest {
    override fun fmethod(i: Int): Boolean {
        return i % 2 == 0
    }
}

// SAM(Single Abstract Method)
val isEven2 = InterTest { i: Int -> i % 2 == 0 }
