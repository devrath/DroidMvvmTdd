package com.demo.code.views

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.code.base.BaseFragment
import com.demo.code.databinding.FragmentPlaylistBinding
import com.demo.code.vm.viewmodels.PlaylistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListFragment : BaseFragment() {

    //private lateinit var playlistViewModel : PlaylistViewModel
    private var _binding: FragmentPlaylistBinding? = null
    private val listAdapter = MyPlaylistRecyclerViewAdapter()
    private val binding get() = _binding!!

    private val playlistViewModel by viewModel<PlaylistViewModel>()

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
        setupListView(binding.playlistList)
        observers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** Initialize the view model **/
    private fun initViewModel() {
        //playlistViewModel = ViewModelProvider(this,playlistViewModelFactory).get(PlaylistViewModel::class.java)
    }

    /** Set the observers for the current screen **/
    private fun observers() {
        playlistViewModel.playList.observe(this as LifecycleOwner) { playList ->
            // Update the UI
            playList.getOrNull()?.let {
                listAdapter.updateList(it)
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

    /** Set up the recycler view **/
    private fun setupListView(view: View?){
        with(view as RecyclerView){
            layoutManager = LinearLayoutManager(activity)
            view.adapter = listAdapter
            view.setHasFixedSize(true);
        }
    }

    /** Inflate the screen **/
    private fun inflateScreen(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
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