package com.example.swipes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class GameFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    private var itemTouchHelper: ItemTouchHelper? = null

    private var adapter: PhotoAdapter? = null

    var onBitmapListener: OnBitmapListener? = null
    set(value) {
        field = value
        adapter?.onBitmapListener = onBitmapListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.swipe_photo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManagerFacade(context)
        layoutManager.scrollEnabled = false
        recyclerView!!.layoutManager = layoutManager

        val width: Int = resources.displayMetrics.widthPixels
        val height: Int = resources.displayMetrics.heightPixels

        adapter = PhotoAdapter(width, height)
        adapter?.onBitmapListener = onBitmapListener

        adapter?.addImage(decodeImageResource(R.drawable.cpp), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.dotnet), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.java), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.javascript), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.kotlin), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.python), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.ruby), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.rust), width, height)
        adapter?.addImage(decodeImageResource(R.drawable.typescript), width, height)

        recyclerView?.adapter = adapter

        val callback = SimpleItemTouchHelperCallback(adapter!!, 0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        callback.rotationAngle = -30f
        callback.maxTranslation = 400f

        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper!!.attachToRecyclerView(recyclerView)
    }

    private fun decodeImageResource(id: Int): Bitmap {
        return BitmapFactory.decodeResource(resources, id)
    }
}
