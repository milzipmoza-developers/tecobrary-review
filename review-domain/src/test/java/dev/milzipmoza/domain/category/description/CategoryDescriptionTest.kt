package dev.milzipmoza.domain.category.description

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CategoryDescriptionTest {

    @Test
    fun `카테고리 설명이 한 글자 미만이면 예외가 발생한다`() {
        assertThrows<CategoryDescriptionOperationException> {
            CategoryDescription("")
        }
    }

    @Test
    fun `카테고리 설명이 20 글자를 초과하면 예외가 발생한다`() {
        assertThrows<CategoryDescriptionOperationException> {
            CategoryDescription("0123456789" +
                    "0123456789" +
                    "0")
        }
    }
}