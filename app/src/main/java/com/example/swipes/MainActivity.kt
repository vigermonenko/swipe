package com.example.swipes

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayHeight: Int = resources.displayMetrics.heightPixels
        val displayWidth: Int = resources.displayMetrics.widthPixels

        val gameFragment = GameFragment()
        val likedPhotosView = LikedPhotosFragment(displayWidth, displayHeight)
        supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_fragment, gameFragment)
                .commit()

        class OnBitmapLikedObserver(
                private val _likedPhotosView: LikedPhotosFragment,
                private val maxHeight: Int,
                private val maxWidth: Int) : OnBitmapListener {
            override fun OnBitmap(bitmap: Bitmap, imagesLeft: Int, liked: Boolean) {
                if (liked) {
                    _likedPhotosView.adapter.addImage(bitmap, maxWidth / 2, maxHeight / 2)
                }
                if (imagesLeft == 0) {
                    supportFragmentManager.beginTransaction()
                            .add(R.id.activity_main_fragment, _likedPhotosView)
                            .commit()
                }
            }
        }

        gameFragment.onBitmapListener = OnBitmapLikedObserver(likedPhotosView, displayHeight, displayWidth)
    }
}