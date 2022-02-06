package dev.milzipmoza.review.mongo.book.mongo

import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
internal class CustomMongoBookRepositoryImplTest {

    @Autowired
    private lateinit var mongoBookRepository: MongoBookRepository

    @Test
    fun findAllByCategoryNo() {
        val documentBook = DocumentBook(
                isbn = "9788966262471",
                detail = DocumentBookDetail(
                        title = "클린아키텍처1",
                        publisher = "위키북스1",
                        author = "로버트마틴1",
                        image = DocumentBookDetailImage(
                                host = "https://www.naver.com",
                                path = "/image.png"
                        ),
                        locale = BookLanguage.KOREAN.toString(),
                        publishDate = LocalDate.now(),
                        description = "로버트마틴의 역작"
                ),
                category = DocumentBookCategory(
                        no = "1",
                        name = "카테고리1",
                        image = DocumentBookCategoryImage(
                                host = "https://www.naver.com",
                                path = "/image.png"
                        )
                ),
                tags = mutableSetOf()
        )
        val entities = listOf(
                documentBook,
                DocumentBook(
                        isbn = "9788966262472",
                        detail = DocumentBookDetail(
                                title = "클린아키텍처2",
                                publisher = "위키북스",
                                author = "로버트마틴",
                                image = DocumentBookDetailImage(
                                        host = "https://www.naver.com",
                                        path = "/image.png"
                                ),
                                locale = BookLanguage.KOREAN.toString(),
                                publishDate = LocalDate.now(),
                                description = "로버트마틴의 역작"
                        ),
                        category = DocumentBookCategory(
                                no = "2",
                                name = "카테고리2",
                                image = DocumentBookCategoryImage(
                                        host = "https://www.naver.com",
                                        path = "/image.png"
                                )
                        ),
                        tags = mutableSetOf()
                )
        )

        mongoBookRepository.saveAll(entities)

        val books = mongoBookRepository.findAllByCategoryNo("1", PageRequest.of(0, 10))

        assertThat(books.totalElements).isEqualTo(1)
        assertThat(books.content[0].id).isEqualTo(documentBook.id)
    }

    @Test
    fun findAllAfterPublishDate() {
        val documentBook = DocumentBook(
                isbn = "9788966262471",
                detail = DocumentBookDetail(
                        title = "클린아키텍처1",
                        publisher = "위키북스1",
                        author = "로버트마틴1",
                        image = DocumentBookDetailImage(
                                host = "https://www.naver.com",
                                path = "/image.png"
                        ),
                        locale = BookLanguage.KOREAN.toString(),
                        publishDate = LocalDate.now(),
                        description = "로버트마틴의 역작"
                ),
                category = DocumentBookCategory(
                        no = "1",
                        name = "카테고리1",
                        image = DocumentBookCategoryImage(
                                host = "https://www.naver.com",
                                path = "/image.png"
                        )
                ),
                tags = mutableSetOf()
        )
        val entities = listOf(
                documentBook,
                DocumentBook(
                        isbn = "9788966262472",
                        detail = DocumentBookDetail(
                                title = "클린아키텍처2",
                                publisher = "위키북스",
                                author = "로버트마틴",
                                image = DocumentBookDetailImage(
                                        host = "https://www.naver.com",
                                        path = "/image.png"
                                ),
                                locale = BookLanguage.KOREAN.toString(),
                                publishDate = LocalDate.now().minusDays(2),
                                description = "로버트마틴의 역작"
                        ),
                        category = DocumentBookCategory(
                                no = "2",
                                name = "카테고리2",
                                image = DocumentBookCategoryImage(
                                        host = "https://www.naver.com",
                                        path = "/image.png"
                                )
                        ),
                        tags = mutableSetOf()
                )
        )
        mongoBookRepository.saveAll(entities)

        val books = mongoBookRepository.findAllAfterPublishDate(LocalDate.now().minusDays(1), PageRequest.of(0, 10))

        assertThat(books.totalElements).isEqualTo(1)
        assertThat(books.content[0].id).isEqualTo(documentBook.id)
    }
}