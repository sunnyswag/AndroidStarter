package com.example.kotlinstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StatFs
import com.example.kotlinstarter.Utils.toxxx

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