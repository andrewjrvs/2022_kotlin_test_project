package com.github.andrewjrvs.kotlin_test_project.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Column

@Entity
@Table(name = "wrd_wordy")
data class Wordy (
    @Id 
    @Column(name = "wrd_id")
    val id: Int=0,

    @Column(name = "wrd_message")
    val message: String = "",

    @Column(name = "wrd_count")
    val wordCount: Int = 0
) {
    override fun toString(): String {
        return "[id: $id, wordCount: $wordCount, message: \"$message\"]"
    }
}