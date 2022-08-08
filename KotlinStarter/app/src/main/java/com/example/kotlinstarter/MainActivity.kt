package com.example.kotlinstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstarter.Utils.toxxx
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun coroutineTest() {
        coroutineTest1()
        GlobalScope.launch {
            coroutineTest2()
        }
        coroutineTest3()
    }

    private fun coroutineTest3() = runBlocking {
        launch { coroutineTest3DoWorld() }
        Log.i("huiqinhuang", "Hello3 ")
    }

    private suspend fun coroutineTest3DoWorld() {
        delay(1000L)
        Log.i("huiqinhuang", "World3! ")
    }

    private suspend fun coroutineTest2() {
        val job = GlobalScope.launch {
            delay(1000L)
            Log.i("huiqinhuang", "Wolrd2! ")
        }
        Log.i("huiqinhuang", "Hello2 ")
        job.join()
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
}