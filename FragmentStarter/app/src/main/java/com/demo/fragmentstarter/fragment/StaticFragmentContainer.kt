package com.demo.fragmentstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.demo.fragmentstarter.R

class StaticFragmentContainer: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_static_container, container, false)

        changeText(root, R.id.static_fragment, R.string.testString)
        changeText(root, R.id.static_fragment1, R.string.testString1)

        return root
    }

    private fun changeText(root: View, fragment: Int, resultStr: Int) {
        root.findViewById<View>(fragment)?.apply {
            findViewById<TextView>(R.id.textView).text = context.getString(resultStr)
        }
    }
}