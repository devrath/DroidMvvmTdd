package com.demo.code.fragments.playlist

import androidx.lifecycle.MutableLiveData
import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.Flow

class PlaylistRepository {

    suspend fun getPlaylists() : Flow<Result<List<PlaylistItem>>>{
        TODO("Not yet implemented")
    }

}