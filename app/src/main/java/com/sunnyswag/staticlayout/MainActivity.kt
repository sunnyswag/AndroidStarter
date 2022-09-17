package com.sunnyswag.staticlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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

        seekBar = findViewById<MusicSeekBar>(R.id.musicSeekBar).apply {
            tryPlay = false
            tryDuration = Pair(50F, 100F)
            duration = Pair(0F, 200F)
            seekProgress = curNum.toFloat()
            onProgressChanged = { seekBar, progress, fromUser ->
                Log.d("huiqinhuang", "progress: $progress")
            }
        }

        handler.postDelayed(runnable, 1000)
//        val blurView = findViewById<BlurView>(R.id.blurView)
//        resources.getDrawable(R.drawable.img)
//        val decorView = window.decorView
//        val rootView = decorView.findViewById<ViewGroup>(androidx.appcompat.R.id.content)
//        blurView.setupWith(findViewById(R.id.rootView), RenderScriptBlur(this))
//            .setOverlayColor(Color.parseColor("#77000000"))
//            .setBlurRadius(3f)

//        imageView.postDelayed ({
//            Blurry.with(this)
//                .color(Color.argb(77, 0, 0, 0))
//                .animate(500)
//                .capture(imageView)
//                .getAsync {
//                    imageView.setImageDrawable(BitmapDrawable(resources, it))
//                }
//
//        }, 0)
    }


}