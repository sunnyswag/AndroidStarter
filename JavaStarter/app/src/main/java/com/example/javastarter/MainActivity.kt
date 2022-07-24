package com.example.javastarter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.javastarter.exception.CustomException
import com.example.javastarter.generics.MyPair
import com.example.javastarter.generics.domain.Apple
import com.example.javastarter.generics.domain.Fruit
import com.example.javastarter.multiprocess.MyRunnable
import com.example.javastarter.multiprocess.MyThread
import com.example.javastarter.reflection.DeskEntity
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        testPair()

//        testException()

//        testReflection()

//        testGenerics()

        testMultiProcess()
        LiveData
    }

    private fun testMultiProcess() {
        MyThread().start()

        Thread(MyRunnable()).start()

        Executors.newFixedThreadPool()
    }

    private fun testGenerics() {
        val flist: List<Fruit> = ArrayList<Apple>()
        //        val first1 = flist[0]

        val slist: ArrayList<in Apple> = ArrayList<Fruit>()
        slist.add(Apple())
        //        slist.add(Fruit() as Apple)
    }

    private fun testReflection() {
        val deskEntityClass = Class.forName("com.example.javastarter.reflection.DeskEntity")
        for (field in deskEntityClass.fields) {
            Log.d("huiqing", field.name)
        }

        for (method in deskEntityClass.methods) {
            Log.d("huiqing", method.name)
        }

        val deskEntity = deskEntityClass.newInstance() as DeskEntity
        val methodSet = deskEntityClass.getMethod("setName", String::class.java)
        methodSet.invoke(deskEntity, "zhangsan")

        val methodGet = deskEntityClass.getMethod("getName")
        val name = methodGet.invoke(deskEntity)

        Log.d("huiqing", "getName: $name")
    }

    private fun testException() {
        throw CustomException("test your exception!")
    }

    private fun testPair() {
        val pair = MyPair<Int, String>(1, "23")
        val first = pair.getFirst()
        val second = pair.getSecond()
    }
}