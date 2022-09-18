package com.sunnyswag.staticlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 1000)
            if (++curNum == 200) {
                curNum = 0
            }
            seekBar.seekProgress = curNum.toFloat()
        }
    }

    var curNum = 0

    lateinit var seekBar: MusicSeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        seekBar = findViewById<MusicSeekBar>(R.id.musicSeekBar).apply {
//            tryPlay = false
//            tryDuration = Pair(50F, 100F)
//            duration = Pair(0F, 200F)
//            seekProgress = curNum.toFloat()
//            onProgressChanged = { seekBar, progress, fromUser ->
//                Log.d("huiqinhuang", "progress: $progress")
//            }
//        }

//        handler.postDelayed(runnable, 1000)
        val blurView = findViewById<BlurView>(R.id.blurView)
        val rootView = findViewById<ConstraintLayout>(R.id.rootView)
        blurView.setupWith(rootView, RenderScriptBlur(this))
            .setBlurRadius(5F)
            .setOverlayColor(Color.parseColor("#77000000"))
    }


}