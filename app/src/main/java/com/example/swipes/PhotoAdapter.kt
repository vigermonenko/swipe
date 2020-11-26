package com.example.swipes

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class PhotoAdapter(private val _x: Int, private val _y: Int) : RecyclerView.Adapter<PhotoAdapter.ItemViewHolder>(), ItemTouchHelperAdapter {
    private val bitmaps: MutableList<Bitmap> = ArrayList()

    var onBitmapListener: OnBitmapListener? = null


    fun addImage(bitmap: Bitmap, width: Int, height: Int) {
        bitmaps.add(Bitmap.createScaledBitmap(bitmap, width, height, true))
    }

    fun addImage(bitmap: Bitmap) {
        bitmaps.add(bitmap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val image = SwipeImage(parent.context, _x, _y)
        image.layoutParams = RecyclerView.LayoutParams(_x, _y)
        return ItemViewHolder(image)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image.bitmap = bitmaps[position]
        holder.image.index = position
        holder.image.rotation = 0f
    }

    override fun getItemCount(): Int {
        return bitmaps.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemDismiss(position: Int, direction: Int) {
        val bitmap: Bitmap = bitmaps.removeAt(position)
        notifyItemRemoved(position)

        when (direction) {
            ItemTouchHelper.LEFT -> {
                onBitmapListener?.OnBitmap(bitmap, itemCount, true)
            }
            ItemTouchHelper.RIGHT -> {
                onBitmapListener?.OnBitmap(bitmap, itemCount, false)
            }
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
        val image: SwipeImage = itemView as SwipeImage

        override fun onItemSelected() {
        }

        override fun onItemClear() {
        }
    }
}