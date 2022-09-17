package com.sunnyswag.staticlayout

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MusicSeekBar: View {

    /** 试听选项是否打开 */
    var tryPlay = false
        set(value) {
            if (tryDuration.first >= duration.first
                && tryDuration.second <= duration.second) {
                field = value
            }
        }

    /** 试听起始和结束位置 */
    var tryDuration = Pair(30F, 60F)
        set(value) {
            if (value.first >= duration.first
                && value.second <= duration.second) {
                field = value
            } else {
                tryPlay = false
            }
        }

    /** 整个 seekBar 的刻度值 */
    var duration = Pair(0F, 120F)
        set(value) {
            if (value.first >= 0 && value.second >= 0) {
                field = value
            }
        }

    /** 外部传入的当前播放的位置 */
    var seekProgress: Float = 50F
        set(value) {
            if (value >= duration.first && value <= duration.second) {
                field = value

                if (userTouching) {
                    return
                }

                if (tryPlay) {
                    if (value > tryDuration.first) {
                        // 在试听区域开始位置及以上才需要绘制
                        _trySubLine.set(getTryPosition(true), _lineTop.toFloat(),
                            if (value < tryDuration.second) getRealPosFromScale(value)
                            else getTryPosition(false), _lineBottom.toFloat())
                    } else {
                        _trySubLine.set(0F, _lineTop.toFloat(), 0F, _lineBottom.toFloat())
                    }
                } else {
                    _norSubLine.set(_lineLeft.toFloat(), _lineTop.toFloat(),
                        getRealPosFromScale(value), _lineBottom.toFloat())
                }

                seekBar.curLength = getRealPosFromScale(value) - _seekBarBlack
                invalidate()
            }

        }

    var onProgressChanged: ((seekBar: MusicSeekBar, progress: Int, fromUser: Boolean) -> Unit)? = null
    var onStartTrackingTouch: ((seekBar: MusicSeekBar) -> Unit)? = null
    var onStopTrackingTouch: ((seekBar: MusicSeekBar) -> Unit)? = null

    /** 用户正在操作中 */
    private var userTouching: Boolean = false

    private val _paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /** seekBar 中轴线四周各边的位置 */
    private var _lineTop: Int = 0
    private var _lineBottom: Int = 0
    private var _lineLeft: Int = 0
    private var _lineRight: Int = 0
    /** seekBar 中轴线的长度 */
    private var _lineLength: Int = 0
    /** seekBar 中轴线四周的圆角 */
    private var _lineCorners: Float = 0F
    /** seekBar 两边空余位置 */
    private var _seekBarBlack: Int = 0

    /** seekBar 中轴线的 RectF */
    private var _line: RectF = RectF()
    /** 非试听情况下，中间的 thumb 滑过的长度 */
    private var _norSubLine: RectF = RectF()
    /** 试听情况下，中间的 thumb 滑过的长度 */
    private var _trySubLine: RectF = RectF()

    /** 中间的 thumb */
    private val seekBar = SeekBar()
    /** 试听区域的 border */
    private val tryStart = RectF()
    private val tryEnd = RectF()

    /** seekBar 的颜色 */
    private var thumbTint = 0
    /** seekBar 滑过的颜色 */
    private var progressTint: Int = 0
    /** seekBar 的背景颜色 */
    private var progressBackgroundTint: Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicSeekBar)
        thumbTint = typedArray.getColor(R.styleable.MusicSeekBar_thumbTint,
            resources.getColor(R.color.white))
        progressTint = typedArray.getColor(R.styleable.MusicSeekBar_progressTint,
            resources.getColor(R.color.white))
        progressBackgroundTint = typedArray.getColor(R.styleable.MusicSeekBar_progressBackgroundTint,
            resources.getColor(R.color.white_opacity_40))

        typedArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        _seekBarBlack = h / 2

        _lineLeft = _seekBarBlack
        _lineRight = w - _seekBarBlack
        _lineTop = _seekBarBlack - _seekBarBlack / 4
        _lineBottom = _seekBarBlack + _seekBarBlack / 4
        _lineLength = _lineRight - _lineLeft

        _lineCorners = (_lineBottom - _lineTop) * 0.45F

        if (tryPlay) {
            initTryRect(tryStart, true)
            initTryRect(tryEnd, false)
        }

        _line.set(_lineLeft.toFloat(), _lineTop.toFloat(),
            _lineRight.toFloat(), _lineBottom.toFloat())
        seekBar.onSizeChanged(_seekBarBlack / 2.0, h.toDouble())

    }

    private fun initTryRect(tryRect: RectF, start: Boolean) {
        tryRect.set(getTryPosition(start), _lineTop.toFloat() - TRY_RECT_EXTRA,
            (getTryPosition(start) + TRY_RECT_EXTRA), _lineBottom.toFloat() + TRY_RECT_EXTRA)
    }

    /**
     * 获取试听的开始和结束位置。
     * [start] 是否为试听位置的开始位置。true -> 开始位置，false -> 结束位置
     */
    private fun getTryPosition(start: Boolean): Float {
        return getRealPosFromScale(if (start) tryDuration.first else tryDuration.second)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // draw seekBar 的背景色
        _paint.color = progressBackgroundTint
        canvas?.drawRoundRect(_line, _lineCorners, _lineCorners, _paint)

        // draw seekBar 进度条颜色
        _paint.color = progressTint
        if (tryPlay) { // draw 试听进度条颜色
            canvas?.drawRoundRect(tryStart, _lineCorners, _lineCorners, _paint)
            canvas?.drawRoundRect(tryEnd, _lineCorners, _lineCorners, _paint)
            canvas?.drawRect(_trySubLine, _paint)
        } else {
            canvas?.drawRoundRect(_norSubLine, _lineCorners, _lineCorners, _paint)
        }

        seekBar.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0F
        val seekPos = getSeekBarPosition(x)
        val curPos = getCurPosition(x)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                onStartTrackingTouch?.let { it(this) }
                userTouching = true
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (tryPlay) {
                    if (x > getTryPosition(true)) {
                        // 在试听区域开始位置及以上才需要绘制
                        _trySubLine.set(getTryPosition(true), _lineTop.toFloat(),
                            if (x < getTryPosition(false)) x
                            else getTryPosition(false), _lineBottom.toFloat())
                    } else {
                        _trySubLine.set(getTryPosition(true), _lineTop.toFloat(),
                            getTryPosition(true), _lineBottom.toFloat())
                    }
                } else {
                    _norSubLine.set(_lineLeft.toFloat(), _lineTop.toFloat(), curPos, _lineBottom.toFloat())
                }
                seekBar.curLength = seekPos

                onProgressChanged?.let { it(this,
                        getScalePosFromReal(curPos - _seekBarBlack).toInt(), true) }
            }
            MotionEvent.ACTION_UP -> {
                if (tryPlay) {
                    // 在试听区域之外时，ACTION_UP 的时候需要回到试听区域
                    if (x < getTryPosition(true) || x > getTryPosition(false)) {
                        _trySubLine.set(getTryPosition(true), _lineTop.toFloat(),
                            getRealPosFromScale(this.seekProgress), _lineBottom.toFloat())
                        seekBar.curLength = getRealPosFromScale(this.seekProgress) - _lineLeft
                    } else {
                        seekBar.curLength = seekPos
                    }
                } else {
                    _norSubLine.set(_lineLeft.toFloat(), _lineTop.toFloat(),
                        curPos, _lineBottom.toFloat())
                    seekBar.curLength = seekPos
                }

                onStopTrackingTouch?.let { it(this) }
                userTouching = false
            }
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    /**
     * 获取当前 touch 的位置
     */
    private fun getCurPosition(x: Float) =
        if (x <= _lineLeft) {
            _lineLeft.toFloat()
        } else if (x >= _lineRight) {
            _lineRight.toFloat()
        } else {
            x
        }

    /**
     * 获取当前左边距 seekBar 的位置，seekBar 绘制的起始位置为 left 边，所以会有一定程度的偏移
     * [x] 为实际触摸的位置
     */
    private fun getSeekBarPosition(x: Float) =
        if (x <= _lineLeft) {
            0F
        } else if (x >= _lineRight) {
            _lineRight.toFloat() - _lineLeft
        } else {
            x - _lineLeft
        }

    /**
     * 获取当前刻度下，实际的绘制位置
     */
    private fun getRealPosFromScale(curScale: Float): Float {
        return curScale * _lineLength / (duration.second - duration.first) + _lineLeft
    }

    /**
     * 获取实际的绘制位置下，当前的刻度
     */
    private fun getScalePosFromReal(curPos: Float): Float {
        return (duration.second - duration.first) * curPos / _lineLength
    }

    companion object {
        const val TRY_RECT_EXTRA = 8
    }

    inner class SeekBar {

        private var _left: Double = 0.0
        private var _top: Double = 0.0
        private var _spaceSize: Double = 0.0

        private lateinit var _bmp: Bitmap
        var curLength: Float = 0F

        /**
         * 当 MusicSeekBar 尺寸发生变化时，SeekBar 尺寸随着改变
         * [radius] 为 SeekBar 按钮的半径
         * [spaceSize] 空余空间的大小，默认正方形
         */
        fun onSizeChanged(radius: Double, spaceSize: Double) {
            _left = spaceSize / 2 - radius
            val right: Double = spaceSize / 2 + radius
            _top = spaceSize / 2 - radius
            val bottom: Double = spaceSize / 2 + radius

            _bmp = Bitmap.createBitmap((right - _left).toInt(), (bottom - _top).toInt(),
                Bitmap.Config.ARGB_8888)
            val bmpCenterX = _bmp.width / 2
            val bmpCenterY = _bmp.height / 2
            val bmpRadius = (right - _left) * 0.5

            val defaultCanvas = Canvas(_bmp)
            val defaultPaint = Paint(Paint.ANTI_ALIAS_FLAG)

            // 绘制 body
            defaultPaint.style = Paint.Style.FILL
            defaultPaint.color = thumbTint
            defaultCanvas.drawCircle(bmpCenterX.toFloat(),
                bmpCenterY.toFloat(), bmpRadius.toFloat(), defaultPaint)
        }

        fun draw(canvas: Canvas?) {
            canvas?.save()
            canvas?.translate(curLength, 0F)
            canvas?.drawBitmap(_bmp, _left.toFloat(), _top.toFloat(), null)
            canvas?.restore()
        }
    }
}