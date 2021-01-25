package com.demo.code.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.code.R
import com.demo.code.models.PlaylistDataItem
import kotlinx.android.synthetic.main.fragment_playlist.*

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflateScreen(inflater,container)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
    }

    private fun inflateScreen(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    private fun setAdapter() {
        // Creates a vertical Layout Manager
        playlist_list.layoutManager = LinearLayoutManager(activity)
        playlist_list.adapter = MyPlaylistRecyclerViewAdapter(setUpListData())
    }

    private fun setUpListData(): ArrayList<PlaylistDataItem> {

        val data1 = PlaylistDataItem("Category1","Id1","Name1")
        val data2 = PlaylistDataItem("Category2","Id2","Name2")
        val data3 = PlaylistDataItem("Category3","Id3","Name3")

        val listData = ArrayList<PlaylistDataItem>()
        listData.add(data1)
        listData.add(data2)
        listData.add(data3)

        return listData
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {

            }
    }
}