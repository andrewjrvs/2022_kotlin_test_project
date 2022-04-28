package com.github.andrewjrvs.kotlin_test_project.utilities

import org.springframework.stereotype.Service;

@Service
class WordyUtilRegexImpl: WordyUtil {

    val wordCountRegex = "\\w+".toRegex()

    override fun CountWords(message: String): Int {
        return wordCountRegex.findAll(message).count()
    }
}
