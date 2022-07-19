package com.example.javastarter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.javastarter.generics.MyPair
import com.example.javastarter.generics.domain.Apple
import com.example.javastarter.generics.domain.Fruit
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testPair()

        val flist: List<Fruit> = ArrayList<Apple>()
        val first1 = flist[0]

        val slist: ArrayList<in Apple> = ArrayList<Fruit>()
        slist.add(Apple())
        slist.add(Fruit() as Apple)
    }

    private fun testPair() {
        val pair = MyPair<Int, String>(1, "23")
        val first = pair.getFirst()
        val second = pair.getSecond()
    }
}