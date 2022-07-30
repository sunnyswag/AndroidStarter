package com.example.algorithmstarter

import java.lang.StringBuilder

object Utils {
    fun getArrayString(numbs: IntArray): String {
        val result = StringBuilder()
        for (numb in numbs) {
            result.append(numb).append(" ")
        }

        return result.toString()
    }
}