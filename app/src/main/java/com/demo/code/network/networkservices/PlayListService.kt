package com.demo.code.network.networkservices

import com.demo.code.models.PlaylistItem
import com.demo.code.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListService @Inject constructor(
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
