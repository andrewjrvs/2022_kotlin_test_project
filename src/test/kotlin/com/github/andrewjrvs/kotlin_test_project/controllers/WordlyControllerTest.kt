package com.github.andrewjrvs.kotlin_test_project.controllers

import com.github.andrewjrvs.kotlin_test_project.utilities.*
import com.github.andrewjrvs.kotlin_test_project.models.*
import com.github.andrewjrvs.kotlin_test_project.repositories.WordyRepository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.AdditionalAnswers
import java.util.Optional
import org.springframework.web.server.NotAcceptableStatusException



@SpringBootTest
class `Wordy Controller Test` {

    @Mock
    lateinit var mockWordyUtil: WordyUtil

    @Mock
    lateinit var mockWordyRepo: WordyRepository

    var wordyController: WordyController? = null

    var wordCount: Int = 0

    @BeforeEach
    fun setUp() {
        /*
         I don't think I need to reset everything, because I think kotlin or JUNIT5
         makes a new instance for every test... but I'd rather be safe then sorry...
         */
        Mockito.reset(mockWordyUtil)
        Mockito.reset(mockWordyRepo)
        wordCount = 0

        // when save is called, update the running count with the word count passed in
        // and return the wordy object...
        Mockito.`when`(mockWordyRepo.save(Mockito.any(Wordy::class.java))).then({
            wordCount += it.getArgument<Wordy>(0).wordCount
            it.getArgument(0)
        })
        // return the running count when Select word Counts is called.
        Mockito.`when`(mockWordyRepo.selectWordCounts()).then({wordCount})

        Mockito.`when`(mockWordyRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty())
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(1)
        
        wordyController = WordyController(mockWordyUtil, mockWordyRepo)
    }

    @Test
    fun `should return an exact count on first call`() {
        val rply = wordyController?.post(WordyRequest(1, "this test"))
        Mockito.verify(mockWordyRepo, Mockito.times(1)).save(Mockito.any())
        assertEquals(1, rply?.count)
    }

    @Test
    fun `should add the second word count to the first in the reply`() {
        wordyController?.post(WordyRequest(1, ""))
        val rply = wordyController?.post(WordyRequest(2, ""))
        Mockito.verify(mockWordyRepo, Mockito.times(2)).save(Mockito.any())
        assertEquals(2, rply?.count)
    }

    @Test
    fun `should ignore submissions with the same ID`() {
        Mockito.`when`(mockWordyRepo.findById(Mockito.anyInt()))
            .thenReturn(Optional.empty())
            .thenReturn(Optional.of(Wordy(1, "")))
            .thenReturn(Optional.of(Wordy(1, "")))
            .thenReturn(Optional.empty())
        wordyController?.post(WordyRequest(1, ""))
        wordyController?.post(WordyRequest(1, ""))
        wordyController?.post(WordyRequest(1, ""))
        val rply = wordyController?.post(WordyRequest(2, ""))
        Mockito.verify(mockWordyRepo, Mockito.times(2)).save(Mockito.any())
        assertEquals(2, rply?.count)
    }

    
    @Test
    fun `should fail when ID is missing`() {
        assertThrows<NotAcceptableStatusException> {
            wordyController?.post(WordyRequest(message="this test"))
        }
        Mockito.verify(mockWordyRepo, Mockito.never()).save(Mockito.any())
    }

    @Test
    fun `should fail when message is missing`() {
        assertThrows<NotAcceptableStatusException> {
            wordyController?.post(WordyRequest(message="testing"))
        }
        Mockito.verify(mockWordyRepo, Mockito.never()).save(Mockito.any())
    }

    @Test
    fun `should fail when id is a negative number`() {
        assertThrows<NotAcceptableStatusException> {
            wordyController?.post(WordyRequest(id=-5, message="testing"))
        }
        Mockito.verify(mockWordyRepo, Mockito.never()).save(Mockito.any())
    }

    @Test
    fun `should return 3 - then 8 `() {
        Mockito.`when`(mockWordyUtil.CountWords(Mockito.anyString())).thenReturn(3).thenReturn(5)
        val rply = wordyController?.post(WordyRequest(1, ""))
        assertEquals(3, rply?.count)
        val rply2 = wordyController?.post(WordyRequest(2, ""))
        Mockito.verify(mockWordyRepo, Mockito.times(2)).save(Mockito.any())
        assertEquals(8, rply2?.count)
    }


}