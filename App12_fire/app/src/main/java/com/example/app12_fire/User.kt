package com.example.app12_fire

class User {
    var id: String = ""
    var name: String = ""
    var password: String = ""

    constructor()
    constructor(id: String, name: String, password: String){
        this.id = id
        this.name = name
        this.password = password
    }
}