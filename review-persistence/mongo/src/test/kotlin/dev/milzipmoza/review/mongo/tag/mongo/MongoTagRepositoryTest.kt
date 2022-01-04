package dev.milzipmoza.review.mongo.tag.mongo

import dev.milzipmoza.review.mongo.extension.Logger
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.domain.PageRequest

@DataMongoTest
class MongoTagRepositoryTest {

    private val logger = Logger.of(this)

    @Autowired
    private lateinit var mongoTagRepository: MongoTagRepository

    private val bookMappingIds = mutableListOf<ObjectId>()

    @BeforeEach
    internal fun setUp() {
        val saveAll = mongoTagRepository.saveAll(
                listOf(
                        DocumentTag(colorCode = "#000000", name = "객체지향", description = "객체지향 패러다임"),
                        DocumentTag(colorCode = "#000011", name = "절차지향", description = "절차지향 패러다임"),
                        DocumentTag(colorCode = "#000022", name = "쉬움", description = "쉬운 책이다"),
                        DocumentTag(colorCode = "#000033", name = "어려움", description = "어려운 책이다"),
                        DocumentTag(colorCode = "#000044", name = "중간", description = "중간 난이도의 책이다"),
                        DocumentTag(colorCode = "#000055", name = "프론트엔드", description = "프론트엔드 개발자에게 추천하는 도서이다."),
                )
        )

        mongoTagRepository.saveAll(saveAll)
    }

    @Test
    fun findByName() {
        val tag = mongoTagRepository.findByName("객체지향")
                ?: throw IllegalArgumentException("찾을 수 없음")

        logger.info("tag={}", tag)

        assertThat(tag.id).isNotNull
        assertThat(tag.colorCode).isEqualTo("#000000")
        assertThat(tag.name).isEqualTo("객체지향")
        assertThat(tag.description).isEqualTo("객체지향 패러다임")
    }

    @Test
    fun findAllByBookMappingId() {
        val pageTags = mongoTagRepository.findAll(PageRequest.of(0, 2))

        logger.info("tags={}", pageTags)
        pageTags.content.forEach {
            logger.info("tag={}", it)
        }

        assertThat(pageTags.totalElements).isEqualTo(bookMappingIds.size.toLong())
        assertThat(pageTags.size).isEqualTo(2)
        assertThat(pageTags.totalPages).isEqualTo(3)
    }

    @Test
    fun findAllByBookMappingId2() {
        val pageTags = mongoTagRepository.findAll(PageRequest.of(0, 2))

        logger.info("tags={}", pageTags)
        pageTags.content.forEach {
            logger.info("tag={}", it)
        }

        assertThat(pageTags.totalElements).isEqualTo(2)
        assertThat(pageTags.size).isEqualTo(2)
        assertThat(pageTags.totalPages).isEqualTo(1)
    }
}