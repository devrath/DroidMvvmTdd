package com.demo.code.fragments.playlist

import com.demo.code.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class PlayListServiceTest : BaseUnitTest() {

    lateinit var service : PlayListService
    private val api : PlaylistAPI = mock()

    /** Here we are verifying from the service
     ** we ae calling fetch Api function **/
    @Test
    fun fetchPlaylistsFromApi() = runBlocking {

        service = PlayListService(api)

        service.fetchPlayList()

        verify(api,times(1)).fetchPlayList()
    }


}