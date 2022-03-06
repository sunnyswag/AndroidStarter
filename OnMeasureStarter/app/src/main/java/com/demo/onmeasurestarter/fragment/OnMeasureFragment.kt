package com.demo.onmeasurestarter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.onmeasurestarter.R
import com.demo.onmeasurestarter.view.SquareImageView
import com.demo.onmeasurestarter.viewmodel.OnMeasureViewModel

class OnMeasureFragment: Fragment() {

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_on_measure, container, false)
        val squareImageView = root.findViewById<SquareImageView>(R.id.square_imageview)

        val viewModel = ViewModelProvider(this).get(OnMeasureViewModel::class.java)

        squareImageView.setOnClickListener {
            val lp: ViewGroup.LayoutParams =  it.layoutParams

            val widthLastTime = viewModel.widthLastTime.value ?: 10
            val heightLastTime = viewModel.heightLastTime.value ?: 500

            viewModel.widthLastTime.value = lp.width
            viewModel.heightLastTime.value = lp.height

            lp.width = widthLastTime
            lp.height = heightLastTime

            it.layoutParams = lp
        }

        return root
    }
}