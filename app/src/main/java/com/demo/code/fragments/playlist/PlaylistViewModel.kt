package com.demo.code.fragments.playlist

import androidx.lifecycle.*
import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
        private val repository : PlaylistRepository
) : ViewModel(){

    val playList = liveData<Result<List<PlaylistItem>>>{
        emitSource(repository.getPlaylists().asLiveData())
    }



}