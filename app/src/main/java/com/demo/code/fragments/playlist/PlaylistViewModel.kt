package com.demo.code.fragments.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.code.models.PlaylistItem

class PlaylistViewModel : ViewModel(){

    private val playList = MutableLiveData<List<PlaylistItem>>()


    fun setUpListData() {

        val data1 = PlaylistItem("Category1","Id1","Name1")
        val data2 = PlaylistItem("Category2","Id2","Name2")
        val data3 = PlaylistItem("Category3","Id3","Name3")

        val listData = ArrayList<PlaylistItem>()
        listData.add(data1)
        listData.add(data2)
        listData.add(data3)

        playList.value = listData
    }


    fun getPlayList() : MutableLiveData<List<PlaylistItem>> {
        return  playList
    }


}