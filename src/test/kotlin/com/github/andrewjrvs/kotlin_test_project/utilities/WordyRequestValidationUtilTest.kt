package com.github.andrewjrvs.kotlin_test_project.utilities

import com.github.andrewjrvs.kotlin_test_project.models.WordyRequest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class `Wordy Request Validation Util Test` {

    @Test
    fun `should return true when all data provided`() { 
        assertTrue(WordyRequestValidationUtil.isValid(WordyRequest(1, "testing")))            
    }

    @Test
    fun `should return fail when ID is missing`() { 
        assertFalse(WordyRequestValidationUtil.isValid(WordyRequest(null, "testing")))            
    }

    @Test
    fun `should return fail when message is missing`() { 
        assertFalse(WordyRequestValidationUtil.isValid(WordyRequest(1, null)))            
    }


}
