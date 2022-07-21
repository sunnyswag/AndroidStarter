package com.example.javastarter.reflection

class DeskEntity {

    var name: String = ""

    var price: Int = 0

    constructor()

    constructor(name: String, price: Int) {
        this.name = name
        this.price = price
    }
}