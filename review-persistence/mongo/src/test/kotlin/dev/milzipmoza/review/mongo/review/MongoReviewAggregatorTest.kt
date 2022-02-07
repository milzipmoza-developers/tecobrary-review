package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.model.Review
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import dev.milzipmoza.review.mongo.extension.Logger
import kotlin.random.Random
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.repository.MongoRepository

@SpringBootTest
internal class MongoReviewAggregatorTest {

    private val log = Logger.of(this)

    @Autowired
    private lateinit var mongoReviewAggregator: MongoReviewAggregator

    @Autowired
    private lateinit var mongoReviewRepository: MongoReviewRepository

    @BeforeEach
    internal fun setUp() {
        val reviews = IntRange(0, 1000)
                .map { create(it.toString()) }
                .map { DocumentReviewMapper.map(it) }
                .toList()

        mongoReviewRepository.saveAll(reviews)
    }

    @Test
    fun getBriefKeywords() {
        val documents = mongoReviewAggregator.getBriefKeywords("a", ReviewReadRange.A_LITTLE.name)

        val mappedResults = documents.mappedResults

        mappedResults.forEach { log.info("$it") }
    }

    @Test
    fun getBriefReviews() {
        val documents = mongoReviewAggregator.getBriefReviews("a", ReviewReadRange.A_LITTLE.name)

        val mappedResults = documents.mappedResults

        mappedResults.forEach { log.info("$it") }
    }

    private fun create(memberNo: String) = Review.SimpleReview(
            member = ReviewMember(no = memberNo),
            book = ReviewBook(isbn = "a", title = "제목"),
            range = ReviewReadRange.values()[Random.nextInt(0, 4) % 5],
            keyword = ReviewKeyword(
                    content = ReviewKeyword.Content.values()[Random.nextInt(0, 4) % 5],
                    informative = ReviewKeyword.Informative.values()[Random.nextInt(0, 3) % 4],
                    readMore = ReviewKeyword.ReadMore.values()[Random.nextInt(0, 2) % 3],
                    selectables = setOf(ReviewKeyword.Selectable.values()[Random.nextInt(0, 8) % 6])
            )
    )
}