package com.artistapp.testutils.unittest.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.artistapp.testutils.unittest.rule.CoroutineTestRule
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule

abstract class ViewModelTestBase {

    @Suppress("LeakingThis")
    @get:Rule
    val mockKRule = MockKRule(this)

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()
}