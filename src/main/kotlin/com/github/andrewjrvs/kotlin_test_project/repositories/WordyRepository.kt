package com.github.andrewjrvs.kotlin_test_project.repositories

import org.springframework.data.jpa.repository.JpaRepository
import com.github.andrewjrvs.kotlin_test_project.models.Wordy
import org.springframework.data.jpa.repository.Query;

public interface WordyRepository: JpaRepository<Wordy, Int> {

    @Query("SELECT SUM(wrd.wordCount) FROM Wordy wrd")
    fun selectWordCounts(): Int?

}
