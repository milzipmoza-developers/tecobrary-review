package dev.milzipmoza.review

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReviewApiApplication

fun main(args: Array<String>) {
    runApplication<ReviewApiApplication>(*args)
}