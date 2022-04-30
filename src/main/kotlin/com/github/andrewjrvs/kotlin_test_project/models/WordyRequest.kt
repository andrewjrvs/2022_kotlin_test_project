package com.github.andrewjrvs.kotlin_test_project.models

data class WordyRequest (
    val id: Int?=null,
    val message: String?=null,
) {
    override fun toString(): String {
        return "[id: $id, message: \"$message\"]"
    }
}