package com.demo.code.fragments.playlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.code.utils.MainCoroutineScopeRule
import com.demo.code.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class PlaylistViewModelTest {

    // For the coroutine scoping
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    // For execution of live data that happen instantly, so we can use the values in the test
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // We create the object of view model
    var playlistViewModel : PlaylistViewModel
    // We mock the repository
    var playlistRepository : PlaylistRepository = mock()

    init {
        playlistViewModel = PlaylistViewModel(playlistRepository)
    }

    @Test
    fun getPlaylistFromRepository(){
        // Extension function for the live data
        playlistViewModel.getPlayList().getValueForTest()
        // Call the method of the repository one time
        verify(playlistRepository, times(1)).getPlaylists()
    }

}