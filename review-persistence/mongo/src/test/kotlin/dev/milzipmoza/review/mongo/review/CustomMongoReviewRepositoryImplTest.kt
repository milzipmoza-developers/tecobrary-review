package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.mongo.extension.Logger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CustomMongoReviewRepositoryTest {


    private val log = Logger.of(this)

    @Autowired
    private lateinit var mongoReviewRepository: MongoReviewRepository

    @BeforeEach
    internal fun setUp() {
        val elements = IntRange(0, 100)
                .map {
                    DocumentReview(
                            member = DocumentReviewMember("$it"),
                            book = DocumentReviewBook(
                                    isbn = "$it",
                                    title = "$it"
                            ),
                            range = "$it",
                            keyword = DocumentReviewKeyword(
                                    content = "$it",
                                    informative = "$it",
                                    readMore = null,
                                    selectables = emptySet()
                            ),
                    )
                }
        mongoReviewRepository.insert(elements)
    }

    @Test
    fun name() {
        val firstPage = mongoReviewRepository.findRecentAfter(10, null)

        log.info("result={}", firstPage)

        val after = firstPage[firstPage.lastIndex]

        val secondPage = mongoReviewRepository.findRecentAfter(10, after.id)

        log.info("result={}", secondPage)
    }
}