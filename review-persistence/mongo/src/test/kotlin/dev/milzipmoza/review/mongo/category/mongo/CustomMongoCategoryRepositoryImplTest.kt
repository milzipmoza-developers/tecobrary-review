package dev.milzipmoza.review.mongo.category.mongo

import dev.milzipmoza.review.mongo.extension.Logger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.TextIndexDefinition

@DataMongoTest
class CustomMongoCategoryRepositoryImplTest {

    private val logger = Logger.of(this)

    @Autowired
    private lateinit var mongoCategoryRepository: MongoCategoryRepository

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @BeforeEach
    internal fun setUp() {
        val textIndexDefinition = TextIndexDefinition.builder()
                .withDefaultLanguage("none")
                .onField("name", 1F)
                .build()

        mongoTemplate.indexOps(DocumentCategory::class.java).ensureIndex(textIndexDefinition)

        mongoCategoryRepository.save(DocumentCategory(
                name = "스프링",
                description = "아무튼 스프링임",
                image = DocumentCategoryImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                )
        ))

        mongoCategoryRepository.save(DocumentCategory(
                name = "객체 지향",
                description = "객체지향 패러다임",
                image = DocumentCategoryImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                )
        ))

        mongoCategoryRepository.save(DocumentCategory(
                name = "절차 지향",
                description = "절차지향 패러다임",
                image = DocumentCategoryImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                )
        ))
    }

    @Test
    fun testName() {
        val page = mongoCategoryRepository.findAllByKeyword("지향", PageRequest.of(0, 10))

        assertThat(page.content.size).isEqualTo(2)
    }
}