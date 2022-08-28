package com.example.kotlinstarter

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.kotlinstarter.Utils.toxxx
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Singleton.getInstance()
        Executors.newCachedThreadPool()

        // 委托
        val testValue: String by lazy {
            "testValue"
        }

        // 高阶函数
        runAdvancedFunction("testAdvancedFunction") {
            println("test success".toxxx("are you OK"))
        }

        "sfd".toxxx("sfd")

        coroutineTest()
    }

    /**
     * 两数相加
        两个链表分别表示两个非负的整数，它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

        请你将两个数相加，并以相同形式返回一个表示和的链表。

        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        输入：l1 = [2,4,3], l2 = [5,6,4]
        输出：[7,0,8]
        解释：342 + 465 = 807.
     */

    private fun coroutineTest() {
        coroutineTest1()
        var d: String = ""
        GlobalScope.launch {
            d = coroutineTest2()
        }
        coroutineTest3(d)
    }

    private fun coroutineTest3(d: String) = runBlocking {
        launch { coroutineTest3DoWorld() }
        Log.i("huiqinhuang", "Hello3 $d")
    }

    private suspend fun coroutineTest3DoWorld() {
        delay(1000L)
        Log.i("huiqinhuang", "World3! ")
    }

    private suspend fun coroutineTest2(): String {
        val job = GlobalScope.launch(Dispatchers.Main) {
            delay(3000L)
            delay(3000L)
            findViewById<TextView>(R.id.textView).visibility = View.GONE
            Log.i("huiqinhuang", "Wolrd2! ${Thread.currentThread().name}")
        }
        Log.i("huiqinhuang", "Hello2 ")
        job.join()
        return "d"
    }

    private fun coroutineTest1() {
        GlobalScope.launch {
            delay(1000L)
            Log.i("huiqinhuang", "Wolrd1! ")
        }
        Log.i("huiqinhuang", "Hello1 ")
        Thread.sleep(2000L)
    }

    // 内联函数
    private inline fun runAdvancedFunction(param: String, run: () -> Unit) {
        println(param)
        run.invoke()
    }

    // 拓展函数
//    fun String.toxxx(param: String) {
//        println("jumpTo $param")
//    }

    /**
     * 二分查找
     * [array] 为需要查找的有序数组
     * [target] 为查找的值
     * @return [target] 在 [array] 中的 index，若未找到，则返回 -1
     */
    fun binarySearch(array: IntArray, target: Int): Int {
        var left = 0
        var right = array.size
        while (left < right) {
            val mid: Int = left + (right - left) / 2
            if (array[mid] < target) {
                left = mid + 1
            } else if (array[mid] > target) {
                right = mid - 1
            } else {
                return mid
            }
        }

        return -1
    }
}