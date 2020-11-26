package com.example.swipes

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class SimpleItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter,
                                    private val _dragFlags: Int,
                                    private val _swipeFlags: Int)
    : ItemTouchHelper.Callback() {

    public var maxTranslation: Float = 0f

    public var rotationAngle: Float = 0f

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition, direction)
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(_dragFlags, _swipeFlags)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            val itemViewHolder: ItemTouchHelperViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemSelected()
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as ItemTouchHelperViewHolder).onItemClear()
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val width: Float = viewHolder.itemView.width.toFloat()
            val alpha: Float = 1f - abs(dX) / width
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.translationX = dX
            viewHolder.itemView.rotation = dX / maxTranslation * rotationAngle
        } else {
            viewHolder.itemView.alpha = 1f
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}