package com.example.schemestarter

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SchemeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_scheme)


        val data = intent.data

        Log.i("huiqinhuang", "host = " + data?.host + " path = " + data?.path + " query = " + data?.query)
        val param = data?.getQueryParameter("OKId")
        findViewById<TextView>(R.id.okOrNot).text = "OKId: $param"
    }
}