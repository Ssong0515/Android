package com.example.myapplication01

// p.115 상속
// 코틀린 클래스는 기본적으로 상속 불가능
// 부모 클래스 open 키워드 추가
// 코틀린 ' : ' 사용 (자바의 extends)
// 코틀린 상속관계에서도 자식 클래스는 부모 클래스의 생성자를 반드시 호출

open class Super {}
class Sub: Super() {}

open class Super1(name: String){}
class Sub1(name: String):Super1(name) {}

open class Super2(name: String) {}
class Sub2:Super2 {
    constructor(name: String) : super(name)
}

open class Super3 {
    var superData = 10
    fun superFun(){
        println("나는 Super3 superData: $superData")
    }
}
class Sub3: Super3()

open class Super117 {
    open var someData = 10
    open fun someFun(){
        println("나는 Super117 superData: $someData")
    }
}
class Sub117 :Super117(){
    // 오버라이딩
    override var someData = 200
    override fun someFun(){
        println("나는 Super117 superData: $someData")
    }
}


fun main(){
    val obj = Sub3()
    obj.superData =20
    obj.superFun()

    val obj117 = Sub117()
    obj.superData = 20
    obj.superFun()

    val pa = Parent("parent")   // sdata = 1, name = parent
    pa.someFun()

    val ch1 = Child1("child")
    ch1.someFun()

    val ch2 = Child2("child")
    ch2.someFun()// sdata = 10, name = child

    var pa1: Parent = Child1("자식")
    pa1.someFun()   // 오버라이딩 된 메서드 실행
}

open class Parent(open val name: String) {
    open var sdata: Int = 1

    open fun someFun(){
        println("sdata: $sdata, name: $name")
    }
}

class Child1(name: String) :Parent(name) {}

class Child2(override val name: String) :Parent(name) {
    override var sdata: Int = 10
//    override var name: String = name
}

// p.118
// public (모두)
// protected (상속관계)
// internal(자바의 default) (같은 모듈)
// private (자신만)
// 코틀린의 기본 접근제한자는 public

// p.120 데이터클래스
class NonDataClass(val name: String, val email: String, val age: Int)
data class DataClass(val name: String, val email: String, val age: Int)
