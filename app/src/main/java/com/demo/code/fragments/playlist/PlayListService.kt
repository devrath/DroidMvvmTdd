package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlayListService(
        private val api : PlaylistAPI
) {

    suspend fun fetchPlayList() : Flow<Result<List<PlaylistItem>>> {

        return flow {
            emit(Result.success(api.fetchPlayList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
