package dev.milzipmoza.review.mongo.category.domain

import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategory
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryImage
import dev.milzipmoza.review.mongo.category.mongo.MongoCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MongoCategoriesTest {

    @Autowired
    private lateinit var mongoCategoryRepository: MongoCategoryRepository

    @Autowired
    private lateinit var mongoCategories: MongoCategories

    private lateinit var categoryNo: String

    @BeforeEach
    internal fun setUp() {
        val document = DocumentCategory(
                colorCode = "#000000",
                name = "스프링",
                description = "아무튼 스프링임",
                image = DocumentCategoryImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                )
        )

        val savedDocument = mongoCategoryRepository.save(document)

        this.categoryNo = savedDocument.id.toHexString()
    }

    @Test
    internal fun `데이터가 존재하면 예외가 발생하지 않는다`() {
        assertDoesNotThrow { mongoCategories.findBy(categoryNo) }
    }

    @Test
    internal fun `데이터가 존재하지 않으면 예외가 발생한다`() {
        assertThrows<DocumentNotFoundException> { mongoCategories.findBy("123456789012345678901234") }
    }
}