package com.example.kotlinstarter

object Utils {
    // 拓展函数
    fun String.toxxx(param: String): String {
        println("${this}jumpTo$param")
        return this
    }
}