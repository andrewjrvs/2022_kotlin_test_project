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

@SpringBootTest
class `Wordy Controller Secuirty Test` {

    @Mock
    lateinit var mockWordyUtil: WordyUtil

    @Mock
    lateinit var mockWordyRepo: WordyRepository

    var wordyController: WordyController? = null

    var wordCount: Int = 0

    // TODO: I should look into setting the up as a base class now...
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
    fun `should not accept a wordCount that was passed in`() {
        // tempted to throw an error, but for now it's just accepting it.
        val rply = wordyController?.post(Wordy(1, "", 9000))
        assertEquals(1, rply?.count)
    }


}