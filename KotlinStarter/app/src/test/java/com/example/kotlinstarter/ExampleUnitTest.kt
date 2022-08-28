package com.example.kotlinstarter

import com.example.kotlinstarter.viewmodel.Algorithm
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTowSum() {
        val list1 = arrayListOf<Int>(2, 4, 3)
        val list2 = arrayListOf<Int>(5, 6, 4)

        var result = Algorithm.plusTowNum(generateData(list1), generateData(list2))

        var res = 0
        while (result != null) {
            res = res * 10 + result.`val`
            result = result.next
        }

        assertEquals(res, 708)
    }

    /**
     * generate a ListNode Data
     */
    private fun generateData(list: ArrayList<Int>): Algorithm.ListNode {
        var cur = Algorithm.ListNode(0)
        val res = cur

        for (item in list) {
            cur.next = Algorithm.ListNode(item)
            cur = cur.next
        }

        return res.next
    }
}