package com.demo.code.fragments.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.demo.code.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.code.R
import com.demo.code.models.PlaylistItem
import kotlinx.android.synthetic.main.fragment_playlist.*

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    private var columnCount = 1

    lateinit var playlistViewModel : PlaylistViewModel
    lateinit var playlistViewModelFactory : PlaylistViewModelFactory

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
        observers()
    }

    private fun observers() {
        playlistViewModel.getPlayList().observe(this as LifecycleOwner, { playlist ->
            // Update the UI

        })
    }

    private fun inflateScreen(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    private fun setAdapter() {
        // Creates a vertical Layout Manager
        playlist_list.layoutManager = LinearLayoutManager(activity)
        playlist_list.adapter = MyPlaylistRecyclerViewAdapter(setUpListData())
    }

    private fun setUpListData(): ArrayList<PlaylistItem> {

        val data1 = PlaylistItem("Category1","Id1","Name1")
        val data2 = PlaylistItem("Category2","Id2","Name2")
        val data3 = PlaylistItem("Category3","Id3","Name3")

        val listData = ArrayList<PlaylistItem>()
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