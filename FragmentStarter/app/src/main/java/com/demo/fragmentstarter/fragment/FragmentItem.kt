package com.demo.fragmentstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.demo.fragmentstarter.R

class FragmentItem: Fragment() {

    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_item, container, false)
        textView = root.findViewById(R.id.textView)

        return root
    }
}