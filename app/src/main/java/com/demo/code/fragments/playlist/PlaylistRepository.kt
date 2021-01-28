package com.demo.code.fragments.playlist

import androidx.lifecycle.MutableLiveData
import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
        private val service: PlayListService
) {

    suspend fun getPlaylists() : Flow<Result<List<PlaylistItem>>>{
        return service.fetchPlayList()
    }

}