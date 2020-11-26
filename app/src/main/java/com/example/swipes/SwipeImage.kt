package com.example.swipes

import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi


class SwipeImage(context: Context, x: Int, y: Int) : View(context) {
    var index: Int = -1

    var bitmap: Bitmap? = null
    set(value) {
        field = Bitmap.createScaledBitmap(value!!, _rect.width(), _rect.height(), true)
    }

    private var imageAlpha: Float = 1f
        get() = field

    private var _x: Float = 0f

    private var _y: Float = 0f

    private val _paint: Paint = Paint()

    private val _matrix: Matrix = Matrix()

    private var _rect: Rect = Rect(0, 0, x, y)

    override fun setAlpha(alpha: Float) {
        imageAlpha = alpha
        invalidate()
        super.setAlpha(alpha)
    }

    fun onTouch(x: Float, y: Float) {
        _x = x
        _y = y
        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        _paint.style = Paint.Style.FILL
        _paint.textSize = 80f

        if (bitmap != null) {
            _paint.alpha = 255
            canvas.drawBitmap(bitmap!!, matrix, _paint)
        }

        if (translationX < 0) {
            _paint.color = Color.argb(255 * (1 - imageAlpha), 0f ,255f, 0f)
            canvas.drawText("Like", _rect.width() / 10f, _rect.height() / 10f, _paint)
        }

        if (translationX > 0) {
            _paint.color = Color.argb(255 * (1 - imageAlpha), 255f, 0f, 0f)
            canvas.drawText("Dislike", _rect.width() / 10f, _rect.height() / 10f, _paint)
        }

        super.onDraw(canvas)
    }

    override fun onSetAlpha(alpha: Int): Boolean {
        imageAlpha = alpha.toFloat()
        return super.onSetAlpha(alpha)
    }
}
