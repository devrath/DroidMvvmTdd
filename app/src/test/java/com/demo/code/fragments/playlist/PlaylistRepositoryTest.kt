package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem
import com.demo.code.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import java.lang.RuntimeException

class PlaylistRepositoryTest : BaseUnitTest() {

    private val service : PlayListService = mock()
    private val playlists = mock<List<PlaylistItem>>()
    // Exception
    private val expectedExpection = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromService() = runBlockingTest{

        val repository = PlaylistRepository(service)
        repository.getPlaylists()

        verify(service, times(1)).fetchPlayList()

    }

    /** We check if the playlist repository is emitting the playlist
     *  that receives from the service **/
    @Test
    fun emitPlaylistFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun emitErrorFromService() = runBlockingTest {
        val service = mockUnSuccessfulCase()
        assertEquals(expectedExpection,service.getPlaylists().first().exceptionOrNull())
    }




    private fun mockSuccessfulCase(): PlaylistRepository {
        runBlocking {
            whenever(service.fetchPlayList()).thenReturn(
                    flow {
                        emit(Result.success(playlists))
                    }
            )
        }
        return PlaylistRepository(service)
    }

    private fun mockUnSuccessfulCase(): PlaylistRepository {
        runBlocking {
            whenever(service.fetchPlayList()).thenReturn(
                    flow {
                        emit(Result.failure(expectedExpection))
                    }
            )
        }
        return PlaylistRepository(service)
    }

}