package dev.milzipmoza.review.domain.category.model.name

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CategoryNameTest {

    @Test
    fun `이름이 두글자 미만이면 예외가 발생한다`() {
        assertThrows<CategoryNameOperationException> {
            CategoryName("1")
        }
    }

    @Test
    fun `이름이 10자를 초과하면 예외가 발생한다`() {
        assertThrows<CategoryNameOperationException> {
            CategoryName("01234567891")
        }
    }

    @Test
    fun `이름이 같으면 같은 것으로 간주한다`() {
        val it = CategoryName("객체지향")
        val other = CategoryName("객체지향")

        val sameAs = it.sameAs(other)

        assertTrue(sameAs)
    }
}