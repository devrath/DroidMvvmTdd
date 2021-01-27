package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchPlayList() : List<PlaylistItem>

}
