package com.demo.code.base

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

/** Common functionalists added here **/
@RunWith(AndroidJUnit4::class)
open class BaseUiTest {

    protected val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        /*IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpClient())
        )*/
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}


