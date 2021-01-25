package com.demo.code.fragments.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.code.models.PlaylistItem

class PlaylistViewModel : ViewModel(){

    private val playList = MutableLiveData<List<PlaylistItem>>()


    fun setUpListData() {

        val data1 = PlaylistItem("rock","1","Hard Rock Cafe")
        val data2 = PlaylistItem("house","2","Chilled House")
        val data3 = PlaylistItem("mixed","3","US TOP 40 HITS")

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