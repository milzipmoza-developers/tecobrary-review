package dev.milzipmoza.review.mongo.book.domain

import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.mongo.book.mongo.DocumentBook
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookDetail
import dev.milzipmoza.review.mongo.book.mongo.DocumentBookDetailImage
import dev.milzipmoza.review.mongo.book.mongo.MongoBookDetailRepository
import dev.milzipmoza.review.mongo.book.mongo.MongoBookRepository
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MongoBooksTest {

    @Autowired
    private lateinit var mongoBookRepository: MongoBookRepository

    @Autowired
    private lateinit var mongoBookDetailRepository: MongoBookDetailRepository

    @Autowired
    private lateinit var mongoBooks: MongoBooks

    private val bookIsbn: String = "9788966262472"

    @BeforeEach
    internal fun setUp() {
        val documentBookDetail = DocumentBookDetail(
                title = "클린아키텍처",
                publisher = "위키북스",
                author = "로버트마틴",
                image = DocumentBookDetailImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                ),
                locale = BookLanguage.KOREAN.toString(),
                publishDate = LocalDate.now(),
                description = "로버트마틴의 역작"
        )

        val savedBookDetail = mongoBookDetailRepository.save(documentBookDetail)

        val documentBook = DocumentBook(
                isbn = "9788966262472",
                detailMappingId = savedBookDetail.id,
                tagsMappingId = ObjectId.get(),
                category = null
        )

        mongoBookRepository.save(documentBook)
    }

    @Test
    fun findByIsbn() {
        val book = mongoBooks.findBy(bookIsbn)

        assertThat(book.isbn).isEqualTo(bookIsbn)

        assertThat(book.detail).isNotNull
        assertThat(book.detail.title).isNotNull
        assertThat(book.detail.publisher).isNotNull
        assertThat(book.detail.author).isNotNull
        assertThat(book.detail.image).isNotNull
        assertThat(book.detail.locale).isNotNull
        assertThat(book.detail.publishDate).isNotNull
        assertThat(book.detail.description).isNotNull
    }
}