package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayListService {

    suspend fun fetchPlayList() : Flow<Result<List<PlaylistItem>>> {
        TODO("Not yet implemented")
    }

}
