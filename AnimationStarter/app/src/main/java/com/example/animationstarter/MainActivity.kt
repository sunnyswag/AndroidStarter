package com.example.animationstarter

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import com.example.animationstarter.view.CircleView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 插值器
        findViewById<TextView>(R.id.textView).animate().apply {
            translationY(500F)
            duration = 2000
            startDelay = 1000
//            interpolator = DecelerateInterpolator() // 减速
            interpolator = AccelerateInterpolator() // 加速
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    Toast.makeText(this@MainActivity, "animation end", Toast.LENGTH_SHORT).show()
                }
            })
            start()
        }


        // 设置更加详细的动画 PropertyValuesHolder

        // 关键帧动画
//        keyFrameAnimation()

        // 多个动画按顺序执行
//        AnimatorSet().apply {
//            playSequentially(singleObjectAnimation(300F), singleObjectAnimation(50F))
//            startDelay = 1000
//            start()
//        }

        // 单个动画
//        singleObjectAnimation(300F).start()

        // 属性动画
//        textViewAnimation()
    }

    private fun keyFrameAnimation() {
        val keyFrame1 = Keyframe.ofFloat(0F, 0F)
        val keyFrame2 = Keyframe.ofFloat(6F, 720F)
        val keyFrame3 = Keyframe.ofFloat(8F, 360F)
        val keyFrame4 = Keyframe.ofFloat(1F, 180F)
        val valueHolder = PropertyValuesHolder.ofKeyframe(
            "translationX", keyFrame1, keyFrame2, keyFrame3, keyFrame4
        )
        val circleView: TextView = findViewById(R.id.textView)
        ObjectAnimator.ofPropertyValuesHolder(circleView, valueHolder).apply {
            startDelay = 1000
            duration = 2000
            start()
        }
    }

    private fun singleObjectAnimation(values: Float): ObjectAnimator {
        val circleView: CircleView = findViewById(R.id.circleView)
        return ObjectAnimator.ofFloat(circleView, "radius", values).apply {
            duration = 1500
        }
    }

    private fun textViewAnimation() {
        val textView = findViewById<TextView>(R.id.textView)

        textView.animate().apply {
            translationX(200F)
            translationY(200F)
            rotation(90F)
            alpha(0.5F)
            startDelay = 1000
            start()
        }

        textView.setOnClickListener {
            Toast.makeText(this, "hello world!", Toast.LENGTH_SHORT).show()
        }
    }
}