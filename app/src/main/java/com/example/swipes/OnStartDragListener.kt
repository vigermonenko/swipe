package com.example.swipes

import androidx.recyclerview.widget.RecyclerView


interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}