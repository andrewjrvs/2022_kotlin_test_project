package com.github.andrewjrvs.kotlin_test_project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinTestProjectApplication

fun main(args: Array<String>) {
	runApplication<KotlinTestProjectApplication>(*args)
}
