package com.demo.code.fragments.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.code.models.PlaylistItem

class PlaylistViewModel : ViewModel(){

    private val playList = MutableLiveData<List<PlaylistItem>>()


    fun getPlayList() : MutableLiveData<List<PlaylistItem>> {
        return  playList
    }


}