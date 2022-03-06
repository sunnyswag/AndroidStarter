package com.demo.onmeasurestarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.onmeasurestarter.R
import com.demo.onmeasurestarter.view.CircleImageView

class OnDrawFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_on_draw, container, false)

        val circleImageView = root.findViewById<CircleImageView>(R.id.circle_imageview)

        return root
    }

}