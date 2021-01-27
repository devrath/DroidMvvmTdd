package com.demo.code.fragments.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.demo.code.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.code.R
import com.demo.code.models.PlaylistItem
import kotlinx.android.synthetic.main.fragment_playlist.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    private var columnCount = 1

    lateinit var playlistViewModel : PlaylistViewModel




    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.3:2999/") // Sometimes it fails and we need to change this
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PlaylistAPI::class.java)
    private var servicePlaylist = PlayListService(api)
    private var repository = PlaylistRepository(service = servicePlaylist)
    private var playlistViewModelFactory = PlaylistViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflateScreen(inflater,container)
        initViewModel()
        observers()
        return view
    }

    private fun initViewModel() {
        playlistViewModel = ViewModelProvider(this,playlistViewModelFactory).get(PlaylistViewModel::class.java)
    }

    private fun observers() {
        //playlistViewModel.setUpListData()
        playlistViewModel.playList.observe(this as LifecycleOwner) { playList ->
            // Update the UI
            if(playList != null){
                // Show the list
                playList.getOrNull()?.let {
                    setAdapter(it)
                }
            } else {
                // Show the error message
                Toast.makeText(activity,"Empty data",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inflateScreen(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    private fun setAdapter(playList : List<PlaylistItem>) {
        // Creates a vertical Layout Manager
        playlist_list.layoutManager = LinearLayoutManager(activity)
        playlist_list.adapter = MyPlaylistRecyclerViewAdapter(playList)
    }



    companion object {
        @JvmStatic
        fun newInstance() = PlayListFragment().apply {}
    }
}