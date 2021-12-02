package dev.milzipmoza.review.domain.category.model

import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CategoryTest {

    @Test
    fun `기존의 색상과 다른 카테고리 색상으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "리액트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imageUrl = CategoryImageUrl("https://www.naver.com/path/image.png")
        )

        val newColor = CategoryColor("#FFFFFF")

        assertDoesNotThrow {
            val editedCategory = category.edit(newColor)
            assertEquals(editedCategory.color, newColor)
        }
    }

    @Test
    fun `기존의 이름과 다른 카테고리 이름으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "리액트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imageUrl = CategoryImageUrl("https://www.naver.com/path/image.png")
        )

        val newUrl = CategoryImageUrl("https://www.naver.com/path/image.png")

        assertDoesNotThrow {
            val editedCategory = category.edit(newUrl)
            assertEquals(editedCategory.imageUrl, newUrl)
        }
    }

    @Test
    fun `기존의 설명과 다른 카테고리 설명으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "스프링부트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imageUrl = CategoryImageUrl("https://www.naver.com/path/image.png")
        )

        val newDescription = CategoryDescription("Pivotal 팀에서 개발한 백엔드 프레임워크")

        assertDoesNotThrow {
            val editedCategory = category.edit(newDescription)
            assertEquals(editedCategory.description, newDescription)
        }
    }
}