package com.example.javastarter.generics

class MyPair<T, K> (
    private val first: T,
    private val second: K
) {

    fun getFirst(): T {
        return first
    }

    fun getSecond(): K {
        return second
    }
}