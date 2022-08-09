package com.example.javastarter.multiprocess

import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.concurrent.thread

class ArrayListProblem {
    private val array = CopyOnWriteArrayList<Int>()

    fun startAdd1() {
        thread {
            for (i in 0..1000) {
                array.add(i)
            }
        }
    }

    fun startAdd2() {
        thread {
            for (i in 1001..2000) {
                array.add(i)
            }
        }
    }

    fun printResult() {
        Thread.sleep(3000)
        Log.d("huiqinhuang", "array length: ${array.size}")

        var ordered = 0
        for (i in 1 until array.size) {
            if (array[i] < array[i - 1]) {
                ordered = i
                break
            }
        }
        Log.d("huiqinhuang", "ordered error: index:  $ordered, " +
                "value: ${array[ordered - 1]}, ${array[ordered]}")
    }
}