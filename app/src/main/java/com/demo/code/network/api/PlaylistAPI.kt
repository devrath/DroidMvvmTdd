package com.demo.code.network.api

import com.demo.code.models.PlaylistItem
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchPlayList() : List<PlaylistItem>

}
