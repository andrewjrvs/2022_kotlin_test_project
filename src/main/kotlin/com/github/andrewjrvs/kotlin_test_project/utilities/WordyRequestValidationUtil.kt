package com.github.andrewjrvs.kotlin_test_project.utilities

import com.github.andrewjrvs.kotlin_test_project.models.WordyRequest
import org.springframework.stereotype.Service;

@Service
interface WordyRequestValidationUtil {
    companion object {
        fun isValid(wordyReq: WordyRequest): Boolean {
            // ID can't be empty
            // my Rule :) ID can't be negative
            if (wordyReq.id ?: -1 < 0) {
                 return false
            }
            
            // message cant be empty
            wordyReq.message ?: return false
            return true
        }
    
    }

   
}