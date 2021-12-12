package dev.milzipmoza.review.domain.tag.model.description

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TagDescriptionTest {

    @Test
    fun `태그 설명이 한 글자 미만이면 예외가 발생한다`() {
        assertThrows<TagDescriptionException> {
            TagDescription("")
        }
    }

    @Test
    fun `태그 설명이 25 글자를 초과하면 예외가 발생한다`() {
        assertThrows<TagDescriptionException> {
            TagDescription("0123456789" +
                    "0123456789" +
                    "012345")
        }
    }
}