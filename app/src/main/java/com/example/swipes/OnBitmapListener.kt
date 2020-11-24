package com.example.swipes

import android.graphics.Bitmap


interface OnBitmapListener {
    fun OnBitmap(bitmap: Bitmap, imagesLeft: Int, liked: Boolean)
}
