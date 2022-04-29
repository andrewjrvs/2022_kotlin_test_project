package com.github.andrewjrvs.kotlin_test_project.controllers

import com.github.andrewjrvs.kotlin_test_project.utilities.*
import com.github.andrewjrvs.kotlin_test_project.models.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
//import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnitRunner

@SpringBootTest
//@RunWith(MockitoJUnitRunner::class)
class `Wordly Controller Test` {

    @Mock
    lateinit var mockWordyUtil: WordyUtil

    var wordyController: WordyController? = null

    @BeforeEach
    fun setUp() {
        Mockito.reset(mockWordyUtil)
        wordyController = WordyController(mockWordyUtil)
    }

    @Test
    fun `should return an exact count on first call`() {
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(1)
        val rply = wordyController?.post(Wordy(1, "this test"))
        assertEquals(1, rply?.count)
    }

    @Test
    fun `should add the second word count to the first in the reply`() {
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(1).thenReturn(2)
        wordyController?.post(Wordy(1, ""))
        val rply = wordyController?.post(Wordy(2, ""))
        assertEquals(3, rply?.count)
    }

    @Test
    fun `should ignore submissions with the same ID`() {
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(1)
        wordyController?.post(Wordy(1, ""))
        wordyController?.post(Wordy(1, ""))
        wordyController?.post(Wordy(1, ""))
        val rply = wordyController?.post(Wordy(2, ""))
        assertEquals(2, rply?.count)
    }

    @Test
    fun `should return 3 - then 8 `() {
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(3).thenReturn(5)
        val rply = wordyController?.post(Wordy(1, ""))
        assertEquals(3, rply?.count)
        val rply2 = wordyController?.post(Wordy(2, ""))
        assertEquals(8, rply2?.count)
    }


}