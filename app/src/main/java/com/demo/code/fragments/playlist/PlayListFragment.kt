package com.demo.code.fragments.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.code.databinding.FragmentPlaylistBinding
import com.demo.code.models.PlaylistItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import javax.inject.Inject

@AndroidEntryPoint
class PlayListFragment : Fragment() {

    lateinit var playlistViewModel : PlaylistViewModel

    private var _binding: FragmentPlaylistBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var playlistViewModelFactory : PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflateScreen(inflater,container)
        initViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViewModel() {
        playlistViewModel = ViewModelProvider(this,playlistViewModelFactory).get(PlaylistViewModel::class.java)
    }

    private fun observers() {
        //playlistViewModel.setUpListData()
        playlistViewModel.playList.observe(this as LifecycleOwner) { playList ->
            // Update the UI
            playList.getOrNull()?.let {
                setupList(binding.playlistList,it)
            }
        }

        playlistViewModel.progressBarVisibility.observe(this as LifecycleOwner) { isVisible ->
            if(isVisible){
                showProgress()
            }else{
                hideProgress()
            }
        }
    }

    private fun setupList(view: View?, playList: List<PlaylistItem>){
        with(view as RecyclerView){
            layoutManager = LinearLayoutManager(activity)
            adapter = MyPlaylistRecyclerViewAdapter(playList)
        }
    }

    private fun inflateScreen(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setAdapter(playList : List<PlaylistItem>) {
        // Creates a vertical Layout Manager
        playlist_list.layoutManager = LinearLayoutManager(activity)
        playlist_list.adapter = MyPlaylistRecyclerViewAdapter(playList)
    }

    private fun hideProgress() {
        binding.simpleProgressBar.visibility = View.GONE
    }

    private fun showProgress() {
        binding.simpleProgressBar.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlayListFragment().apply {}
    }
}