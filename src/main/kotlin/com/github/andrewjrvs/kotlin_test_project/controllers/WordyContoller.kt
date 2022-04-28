package com.github.andrewjrvs.kotlin_test_project.controllers

import org.springframework.web.bind.annotation.*
import com.github.andrewjrvs.kotlin_test_project.models.*
import com.github.andrewjrvs.kotlin_test_project.utilities.WordyUtil

@RestController
@RequestMapping("/api/wordy")
class WordyController(private val wordyUtil: WordyUtil) {

    // NOTE: this is a placeholder for a db type backend.
    var activeCount: Int = 0
    var currentIds = ArrayList<Int>()

    @GetMapping
    fun index(): String {
        return "Placeholder"
    }

    @PostMapping
    fun post(@RequestBody wordy: Wordy): WordyReply {
        if (!currentIds.contains(wordy.id)){
            currentIds.add(wordy.id)
            this.activeCount += wordyUtil.CountWords(wordy.message)
        }
        return WordyReply(this.activeCount)
    }
}