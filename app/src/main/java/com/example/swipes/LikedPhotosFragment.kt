package com.example.swipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class LikedPhotosFragment(private val _width: Int, private val _height: Int) : Fragment() {
    var itemTouchHelper: ItemTouchHelper? = null

    private var recyclerView: RecyclerView? = null

    var adapter: PhotoAdapter = PhotoAdapter(_height, _height)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.swipe_photo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManagerFacade(context)
        layoutManager.scrollEnabled = true

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        val callback = SimpleItemTouchHelperCallback(adapter, 0 ,0)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper!!.attachToRecyclerView(recyclerView)
    }
}
