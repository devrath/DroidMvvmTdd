package com.demo.code.network.networkservices

import com.demo.code.models.PlaylistItem
import com.demo.code.network.api.PlaylistAPI
import com.demo.code.network.networkservices.PlayListService
import com.demo.code.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.times
import java.lang.RuntimeException

class PlayListServiceTest : BaseUnitTest() {

    lateinit var service : PlayListService
    private val api : PlaylistAPI = mock()
    // We mock the list of play list
    private val playList = mock<List<PlaylistItem>>()
    // Exception
    private val expectedExpection = RuntimeException("Something went wrong")

    /** Here we are verifying from the service
     ** we ae calling fetch Api function **/
    @Test
    fun fetchPlaylistsFromApi() = runBlockingTest {

        service = PlayListService(api)

        service.fetchPlayList().first()

        verify(api,times(1)).fetchPlayList()
    }

    @Test
    fun convertFlowValuesToUnitTestAndEmitThem() = runBlockingTest {

        whenever(api.fetchPlayList()).thenReturn(playList)
        service = PlayListService(api)

        assertEquals(Result.success(playList),service.fetchPlayList().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {

        whenever(api.fetchPlayList()).thenThrow(expectedExpection)
        service = PlayListService(api)

        assertEquals("Something went wrong",service.fetchPlayList().first().exceptionOrNull()?.message)
    }

}