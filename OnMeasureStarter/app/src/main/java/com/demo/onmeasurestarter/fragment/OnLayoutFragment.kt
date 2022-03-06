package com.demo.onmeasurestarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.onmeasurestarter.R
import com.demo.onmeasurestarter.view.SquareImageView

class OnLayoutFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_on_layout, container, false)

        val squareImageView = root.findViewById<SquareImageView>(R.id.square_imageview)

        return root
    }
}