package com.mohaeyo.domain

import org.junit.Before
import org.junit.ClassRule
import org.mockito.MockitoAnnotations

open class BaseTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxSchedulerRule()
    }

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }
}