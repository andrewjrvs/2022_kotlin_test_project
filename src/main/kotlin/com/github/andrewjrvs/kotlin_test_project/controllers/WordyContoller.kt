package com.github.andrewjrvs.kotlin_test_project.controllers

import org.springframework.web.bind.annotation.*
import com.github.andrewjrvs.kotlin_test_project.models.*
import com.github.andrewjrvs.kotlin_test_project.utilities.WordyUtil
import com.github.andrewjrvs.kotlin_test_project.repositories.WordyRepository

@RestController
@RequestMapping("/api/wordy")
class WordyController(private val wordyUtil: WordyUtil, private val wordyRepo: WordyRepository) {

    @GetMapping
    fun index(): String {
        return "Placeholder"
    }

    @PostMapping
    fun post(@RequestBody wordy: Wordy): WordyReply {
        // I'd rather move this to the repository, but for time
        // I'll just do a quick ID check...
        // if the ID doesn't exist... then let's add it
        if (!this.wordyRepo.findById(wordy.id).isPresent()) {
            // ok, I would LOVE to have moved this into the repository, but I'm 
            // having some problems getting this into the save process, so
            // for now... here it lies... might be a problem if we ever
            // allow for updating... maybe if I have time I will move this over to aspectj...
            val svWrd = wordy.copy(wordCount=this.wordyUtil.CountWords(wordy.message))
            this.wordyRepo.save(svWrd)
            println(svWrd)
        }
        var activeCount: Int? = this.wordyRepo.selectWordCounts()
        
        // return a reply object, with the Active count... 
        return WordyReply(activeCount?: 0)
    }
}