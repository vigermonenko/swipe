package com.example.swipes

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager


class LinearLayoutManagerFacade(private val context: Context?) : LinearLayoutManager(context) {
    var scrollEnabled: Boolean = true

    override fun canScrollVertically(): Boolean {
        return scrollEnabled && super.canScrollVertically()
    }
}
