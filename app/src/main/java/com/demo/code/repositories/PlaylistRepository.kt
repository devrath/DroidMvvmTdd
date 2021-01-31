package com.demo.code.repositories

import com.demo.code.network.networkservices.PlayListService
import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
        private val service: PlayListService
) {

    suspend fun getPlaylists() : Flow<Result<List<PlaylistItem>>>{
        return service.fetchPlayList()
    }

}