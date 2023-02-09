package com.testapp.repository.networkapi.util

import com.artistapp.model.resource.Resource
import com.artistapp.repository.networkapi.network.NetworkApiCallDelegate
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Rule

abstract class NetworkRepositoryTestBase<T : NetworkApiCallDelegate> {

    @Suppress("LeakingThis")
    @get:Rule
    val mockKRule = MockKRule(this)

    @MockK
    protected lateinit var mockNetworkApiCallDelegate: NetworkApiCallDelegate

    @InjectMockKs
    protected lateinit var subject: T

    protected infix fun <T : Any> ApiCallBlock<T>.returns(responseData: T) {
        coEvery {
            apiCall()
        } returns responseData

        coEvery {
            mockNetworkApiCallDelegate.executeApiCall(any<suspend () -> T>())
        } coAnswers { Resource.Success(apiCall()) }
    }

    protected fun <T : Any> everyApiCallExecutionOf(apiCall: suspend () -> T) =
        ApiCallBlock(apiCall)

    protected class ApiCallBlock<T : Any>(val apiCall: suspend () -> T)
}