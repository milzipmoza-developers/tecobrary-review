package dev.milzipmoza.review.domain.tag.model.color

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TagColorTest {

    @Test
    fun `컬러 코드는 # 으로 시작하는 총 7글자로 설정할 수 있다`() {
        assertDoesNotThrow {
            TagColor("#000000")
        }
    }

    @Test
    fun `컬러 코드가 # 으로 시작하지 않으면 예외가 발생한다`() {
        assertThrows<TagColorException> {
            TagColor("*0000000")
        }
    }

    @Test
    fun `컬러 코드가 7 글자가 아니면 예외가 발생한다`() {
        assertThrows<TagColorException> {
            TagColor("#000")
        }
    }

    @Test
    fun `컬러 코드가 같으면 같은 색상으로 간주한다`() {
        val it = TagColor("#121212")
        val other = TagColor("#121212")

        val sameAs = it.sameAs(other)

        assertTrue(sameAs)
    }
}