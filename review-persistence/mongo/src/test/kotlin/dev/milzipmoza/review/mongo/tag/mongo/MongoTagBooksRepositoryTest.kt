package dev.milzipmoza.review.mongo.tag.mongo

import dev.milzipmoza.review.mongo.extension.Logger
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.domain.PageRequest

@DataMongoTest
class MongoTagBooksRepositoryTest {

    private val logger = Logger.of(this)

    @Autowired
    private lateinit var mongoTagBooksRepository: MongoTagBooksRepository

    @BeforeEach
    internal fun setUp() {
        mongoTagBooksRepository.saveAll(
                listOf(
                        DocumentTagBooks(books = listOf("1")),
                        DocumentTagBooks(books = listOf("1", "2")),
                        DocumentTagBooks(books = listOf("1", "2", "3")),
                        DocumentTagBooks(books = listOf("1", "2", "3", "4")),
                        DocumentTagBooks(books = listOf("1", "2", "3", "4", "5")),
                )
        )
    }

    @Test
    fun findAllOfContainBook() {
        val tagBooks = mongoTagBooksRepository.findAllOfContainBook("1", PageRequest.of(0, 2))

        logger.info("tagBooks={}", tagBooks)
        tagBooks.content.forEach {
            logger.info("tagBook={}", it)
        }

        assertThat(tagBooks.totalElements).isEqualTo(5)
        assertThat(tagBooks.size).isEqualTo(2)
        assertThat(tagBooks.totalPages).isEqualTo(3)
        assertThat(tagBooks.content.size).isEqualTo(2)
    }
}