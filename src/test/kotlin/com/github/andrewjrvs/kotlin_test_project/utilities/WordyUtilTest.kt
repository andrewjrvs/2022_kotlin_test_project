
package com.github.andrewjrvs.kotlin_test_project.utilities


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
internal class `Wordy Util Test` {

    private val wordyUtil_regex: WordyUtil = WordyUtilRegexImpl()

    companion object {

        val tests_word_examples = listOf(
            Triple(1, "single", "should match a single word")
            , Triple(7, "this is a Sentence with multiple words", "should get the count of multiple words")
            , Triple (2, "Two     Words", "should igore whitespace between words")
            , Triple (1, "  around    ", "should ignore whitespace around words")
        )

        val tests_punctuation_examples = listOf(
            Triple(9, "The boy hit the ball. See the ball fly.", "should get a count with a period")
            , Triple(4, "She ran down-ward", "should treat hyphenated words as multiple words")
            
        )
    }

    @Nested
    @DisplayName("WordyUtil Regex CountWords()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class WordyUtil_Regex {
        @TestFactory
        fun `CountWords with whitespace`() = tests_word_examples
            .map { 
                DynamicTest.dynamicTest(it.third) {
                    assertEquals(it.first, wordyUtil_regex.CountWords(it.second))
                }
            }

        @TestFactory
        fun `CountWords with punctuation`() = tests_punctuation_examples
            .map { 
                DynamicTest.dynamicTest(it.third) {
                    assertEquals(it.first, wordyUtil_regex.CountWords(it.second))
                }
            }
    }


    


}
