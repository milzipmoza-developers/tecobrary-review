package dev.milzipmoza.review.mongo.category.domain

import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl
import dev.milzipmoza.review.mongo.category.mongo.MongoCategoryRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MongoCategoryOperationTest {

    @Autowired
    private lateinit var mongoCategoryRepository: MongoCategoryRepository

    @Autowired
    private lateinit var mongoCategories: MongoCategories

    @Autowired
    private lateinit var mongoCategoryOperation: MongoCategoryOperation

    @AfterEach
    internal fun tearDown() {
        mongoCategoryRepository.deleteAll()
    }

    @Test
    fun `데이터를 성공적으로 저장한다`() {
        val category = Category(
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "스프링"),
                description = CategoryDescription(description = "아무튼 스프링임"),
                imageUrl = CategoryImageUrl(host = "https://www.naver.com", path = "/image.png")
        )

        val categoryNo = assertDoesNotThrow { mongoCategoryOperation.save(category) }
        assertTrue(categoryNo.isNotBlank())
    }

    @Test
    fun `데이터를 업데이트 할 수 있다`() {
        val category = Category(
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "스프링"),
                description = CategoryDescription(description = "아무튼 스프링임"),
                imageUrl = CategoryImageUrl(host = "https://www.naver.com", path = "/image.png")
        )

        val savedCategoryNo = assertDoesNotThrow { mongoCategoryOperation.save(category) }

        val savedCategory = mongoCategories.findBy(savedCategoryNo)

        val toUpdateCategory = savedCategory.edit(CategoryColor("#000001"))

        assertDoesNotThrow { mongoCategoryOperation.update(savedCategory.no, toUpdateCategory) }
    }
}