package com.example.algorithmstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testSort()
        ThreadPoolExecutor()
    }

    private fun testSort() {
        val nums: IntArray = intArrayOf(5, 2, 5, 8, 1, 9, 11)
        Sort.bubbleSort(nums)
        Log.i("huiqinhuang", Utils.getArrayString(nums))
    }



}