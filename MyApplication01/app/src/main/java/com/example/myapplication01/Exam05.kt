package com.example.myapplication01

// p94 컬렉션 타입 ==> Array, List, Set, Map
fun main() {
    val data1:Array<Int> = Array(3, {0})
    println(data1)
    println(data1[0])
    data1[1] = 100  // 배열 접근 [index] 사용
    data1[2] = 200
    for(i in data1) {
        println(i)

    }
    println(data1.get(1))   // set(), get()
    data1.set(1,1000)
    println("data[1] : ${data1.get(1)}")
    println("data1배열 크기 : ${data1.size}")
//    코틀린은 기본 타입 배열 클래스 사용하여 배열 생성
//    ByreArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray, BooleanArray, CharArray 클래스 제공
    val arrInt = IntArray(3,{0})
    val arrBool = BooleanArray(5,{false})

//    배열과 선언과 동시에 초기값 설정(p95)
//    arrayOf()
//    기본타입 사용하여 배열 생성
//    ByreArrayOf(), ShortArrayOf(), IntArrayOf(), LongArrayOf(), FloatArrayOf(),
//    DoubleArrayOf(), BooleanArrayOf(), CharArrayOf()
    val arrdata1 = intArrayOf(10,20,30)
    val arrdata2 = booleanArrayOf(true, false, true)
    println(arrdata2[0])

//    LIST
//    리스트(불변 클래스)
    var list1:List<Int> = List(3,{0})
    // 생성, 초기화 동시에
    var list2 = listOf<Int>(10, 20, 30)
    println("list2 크기: ${list2.size}")
    println("list2 값: ${list2[0]}, ${list2.get(1)}, ${list2.get(2)}")
//    list2[0] = 100
//    list2[0] = 100
//    list2.set(2, 200)

//    가변 클래스로 리스트 생성
    var list3: MutableList<Int> = MutableList(3, {0})
//    생성, 초기화 동시에 ==> mutableListOf
    var list4 = mutableListOf<Int>(10, 20, 30)
    println("list4 크기: ${list4.size}")
    println("list4 값: ${list4[0]}, ${list4.get(1)}, ${list4.get(2)}")
    list4[0] = 100
    list4[0] = 100
    list4.set(2, 200)
    println("list4 값: ${list4[0]}, ${list4.get(1)}, ${list4.get(2)}")

    list4.add(3, 40)
    list4.set(3, 50)
    println(list4)
    for (i in list4) {
        println(i)
    }

//    Map => mapOf 불변
    var map1 = mapOf<String, String>(Pair("one", "hello"), "two" to "world")
    println("""
        mapSize: ${map1.size}
        mapData: ${map1.get("one")}
    """.trimIndent())

//    map1.set("two", "worldworld") 오류 -> 불변

    var map2 = mutableMapOf<String, String>(Pair("oneone", "hello"), "twotwo" to "world")
    println("""
        map2size: ${map2.size}
        map2data: ${map2.get("oneone")}, ${map2.get("twotwo")}
    """.trimIndent())

    map2.set("twotwo", "worldworld") // 수정가능
    println("map2data: ${map2.get("twotwo")}")
    println(map2.keys) // 모든 키값
    println(map2.values) // 모든 값들만
    println("map2.count: " + map2.count()) // 맵의 크기
    println("map2: " + map2)
    map2.set("oneone1", "hello 대신 들어가는 값")
    map2.put("oneone1", "hello2")
    map2.remove("oneone")
    println("map2: " + map2)
    println(map2.contains("oneone"))

    //====================
    // Set
    val set1 = mutableSetOf("one", "two", "three", "four", "five")



}
