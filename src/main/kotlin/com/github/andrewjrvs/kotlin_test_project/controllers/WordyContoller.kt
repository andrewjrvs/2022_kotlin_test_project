package com.github.andrewjrvs.kotlin_test_project.controllers


import com.github.andrewjrvs.kotlin_test_project.models.*
import com.github.andrewjrvs.kotlin_test_project.utilities.*
import com.github.andrewjrvs.kotlin_test_project.repositories.WordyRepository

import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.server.NotAcceptableStatusException

@RestController
@RequestMapping("/api/wordy")
class WordyController(private val wordyUtil: WordyUtil, private val wordyRepo: WordyRepository) {

    @GetMapping
    fun index(): String {
        return "Placeholder"
    }

    @PostMapping
    fun post(@RequestBody wordyReq: WordyRequest): WordyReply {
        // I'd rather move this to the repository, but for time
        // I'll just do a quick ID check...
        // if the ID doesn't exist... then let's add it
        
        // tries to get this into a validation / but ran out of time
        // right now it's hard coded here...

        if (!WordyRequestValidationUtil.isValid(wordyReq)) {
            throw NotAcceptableStatusException("Submitted Object invalid")
        }
        wordyReq.id.let {
            val message = wordyReq.message ?: ""
            val id = wordyReq.id ?: -1
            // I'll admit, I don't need to do the id > -1 check here.. 
            // but the fact I can't assume id is Int here makes this call 
            // a little weird...
            if (id > -1 && !this.wordyRepo.findById(id).isPresent()) {
                
                // ok, I would LOVE to have moved this into the repository, but I'm 
                // having some problems getting this into the save process, so
                // for now... here it lies... might be a problem if we ever
                // allow for updating... maybe if I have time I will move this over to aspectj...
                val svWrd = Wordy(id=id, message=message, wordCount=this.wordyUtil.CountWords(message))
                this.wordyRepo.save(svWrd)

            }
        }
        var activeCount: Int? = this.wordyRepo.selectWordCounts()
        
        // return a reply object, with the Active count... 
        return WordyReply(activeCount?: 0)
    }

}