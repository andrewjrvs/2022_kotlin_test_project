package com.github.andrewjrvs.kotlin_test_project.models

// import javax.persistence.Entity
// import javax.persistence.GeneratedValue
// import javax.persistence.GenerationType
// import javax.persistence.Id


//@Entity
data class Wordy(
    // @Id 
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val message: String = "",

    val wordCount: Int = 0
)