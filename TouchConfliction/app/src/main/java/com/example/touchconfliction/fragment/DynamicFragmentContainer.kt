package com.example.touchconfliction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.touchconfliction.DemoRecyclerAdapter
import com.example.touchconfliction.R

class DynamicFragmentContainer: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.dynamicfragment_container, container, false)
        root.findViewById<RecyclerView>(R.id.recyclerDemo).apply {
            adapter = DemoRecyclerAdapter()
            layoutManager  = LinearLayoutManager(context)
        }

        return root
    }

}