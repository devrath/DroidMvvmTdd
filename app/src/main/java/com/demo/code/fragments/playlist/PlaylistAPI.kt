package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem

interface PlaylistAPI {

    suspend fun fetchPlayList() : List<PlaylistItem> {
        TODO("Not yet implemented")
    }

}
