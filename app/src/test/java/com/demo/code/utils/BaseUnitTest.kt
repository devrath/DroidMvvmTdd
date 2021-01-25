package com.demo.code.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {

    // For the coroutine scoping
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    // For execution of live data that happen instantly, so we can use the values in the test
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}