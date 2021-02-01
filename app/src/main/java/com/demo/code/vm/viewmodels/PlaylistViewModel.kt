package com.demo.code.vm.viewmodels

import androidx.lifecycle.*
import com.demo.code.repositories.PlaylistRepository
import com.demo.code.models.PlaylistItem

class PlaylistViewModel(
        private val repository : PlaylistRepository
) : ViewModel(){

    val progressBarVisibility = MutableLiveData<Boolean>()

    val playList = liveData<Result<List<PlaylistItem>>>{
        // Live data for the loader is emitted
        progressBarVisibility.postValue(true)
        emitSource(repository.getPlaylists().asLiveData())
        progressBarVisibility.postValue(false)
    }



}