package dev.milzipmoza.review.domain.category.model.description

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
    fun `카테고리 설명이 25 글자를 초과하면 예외가 발생한다`() {
        assertThrows<CategoryDescriptionOperationException> {
            CategoryDescription("0123456789" +
                    "0123456789" +
                    "012345")
        }
    }
}