package com.demo.code.viewmodels

import com.demo.code.models.PlaylistItem
import com.demo.code.repositories.PlaylistRepository
import com.demo.code.utils.BaseUnitTest
import com.demo.code.utils.captureValues
import com.demo.code.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
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

        // Get the live data
        viewModel.playList.getValueForTest()

        // Call the method of the repository one time
        verify(playlistRepository, times(1)).getPlaylists()
    }

    @Test
    fun showLoaderWhileLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        // First capture all the emissions of loader live data
        viewModel.progressBarVisibility.captureValues {
            // We force the playlist live data to emit the result
            viewModel.playList.getValueForTest()
            // Check the assertion: First value emitted by loader live data is true
            assertEquals(true,values[0])
        }
    }

    @Test
    fun hideSpinnerAfterLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        // First capture all the emissions of loader live data
        viewModel.progressBarVisibility.captureValues {
            // We force the playlist live data to emit the result
            viewModel.playList.getValueForTest()
            // Check the assertion: Last value emitted by loader live data is false
            assertEquals(false,values.last())
        }
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