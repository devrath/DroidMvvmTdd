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
import org.junit.runner.notification.Failure
import org.mockito.Mockito.times
import java.lang.RuntimeException


class PlaylistViewModelTest : BaseUnitTest() {

    // We mock the repository
    private var playlistRepository : PlaylistRepository = mock()
    // We mock the list of play list
    private val playList = mock<List<PlaylistItem>>()
    // Successful playlist
    private val expected = Result.success(playList)
    // Exception
    private val expectedExpection = RuntimeException("Something went wrong")

    @Test
    fun emitsPlaylistFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected,viewModel.playList.getValueForTest())
    }

    @Test
    fun emitsErrorFromRepository() = runBlockingTest {
        val viewModel = mockUnSuccessfulCase()
        viewModel.playList.getValueForTest().let {
            if (it != null) {
                assertEquals(expectedExpection, it.exceptionOrNull())
            }
        }
    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        // Extension function for the live data
        viewModel.playList.getValueForTest()

        // Call the method of the repository one time
        verify(playlistRepository, times(1)).getPlaylists()
    }





    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(playlistRepository.getPlaylists()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }
        return PlaylistViewModel(playlistRepository)
    }

    private fun mockUnSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(playlistRepository.getPlaylists()).thenReturn(
                    flow {
                        emit(Result.failure<List<PlaylistItem>>(expectedExpection))
                    }
            )
        }
        return PlaylistViewModel(playlistRepository)
    }



}