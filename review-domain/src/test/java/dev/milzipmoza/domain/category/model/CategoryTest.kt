package dev.milzipmoza.domain.category.model

import dev.milzipmoza.domain.category.model.color.CategoryColor
import dev.milzipmoza.domain.category.model.description.CategoryDescription
import dev.milzipmoza.domain.category.model.Category
import dev.milzipmoza.domain.category.model.name.CategoryName
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CategoryTest {

    @Test
    fun `기존의 색상과 다른 카테고리 색상으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "리액트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imagePath = ""
        )

        val newColor = CategoryColor("#FFFFFF")

        assertDoesNotThrow {
            category.change(newColor)
        }
        assertEquals(category.color, newColor)
    }

    @Test
    fun `기존의 이름과 다른 카테고리 이름으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "리액트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imagePath = ""
        )

        val newName = CategoryName("스프링부트")

        assertDoesNotThrow {
            category.change(newName)
        }
        assertEquals(category.name, newName)
    }

    @Test
    fun `기존의 설명과 다른 카테고리 설명으로 변경할 수 있다`() {
        val category = Category(
                no = "123",
                color = CategoryColor(code = "#000000"),
                name = CategoryName(name = "스프링부트"),
                description = CategoryDescription(description = "Meta 에서 개발한 프론트엔드 라이브러리"),
                imagePath = ""
        )

        val newDescription = CategoryDescription("Pivotal 팀에서 개발한 백엔드 프레임워크")

        assertDoesNotThrow {
            category.edit(newDescription)
        }
        assertEquals(category.description, newDescription)
    }
}