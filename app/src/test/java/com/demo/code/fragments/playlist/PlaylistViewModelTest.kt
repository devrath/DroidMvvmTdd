package com.demo.code.fragments.playlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.code.models.PlaylistItem
import com.demo.code.utils.BaseUnitTest
import com.demo.code.utils.MainCoroutineScopeRule
import com.demo.code.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times


class PlaylistViewModelTest : BaseUnitTest() {

    // We create the object of view model
    private lateinit var playlistViewModel : PlaylistViewModel
    // We mock the repository
    private var playlistRepository : PlaylistRepository = mock()
    // We mock the list of play list
    private val playList = mock<List<PlaylistItem>>()
    // Successful playlist
    private val expected = Result.success(playList)



    @Before
    fun setUp() {
        runBlocking {
            whenever(playlistRepository.getPlaylists()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }
        playlistViewModel = PlaylistViewModel(playlistRepository)
    }


    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        // Call the method of the repository one time
        verify(playlistRepository, times(1)).getPlaylists()

        // Extension function for the live data
        playlistViewModel.getPlayList().getValueForTest()

    }

    @Test
    fun emitsPlaylistFromRepository() = runBlockingTest {

        assertEquals(expected,playlistViewModel.getPlayList().getValueForTest())
    }

}