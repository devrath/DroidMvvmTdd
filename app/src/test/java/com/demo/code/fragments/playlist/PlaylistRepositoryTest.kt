package com.demo.code.fragments.playlist

import com.demo.code.models.PlaylistItem
import com.demo.code.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class PlaylistRepositoryTest : BaseUnitTest() {

    private val service : PlayListService = mock()
    private val playlists = mock<List<PlaylistItem>>()

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

        whenever(service.fetchPlayList()).thenReturn(
                flow {
                    emit(Result.success(playlists))
                }
        )

        val repository = PlaylistRepository(service)
        assertEquals(playlists,repository.getPlaylists().first().getOrNull())

    }

}