package dev.milzipmoza.review.domain.book.model.tag

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookTagsTest {

    @Test
    fun containsEquals() {
        val it = BookTags(
                setOf(
                        BookTag("1", "쉬움", "#000000"),
                        BookTag("2", "어려움", "#00FF00"),
                )
        )

        val other = setOf(
                BookTag("1", "쉬움", "#000000"),
                BookTag("2", "어려움", "#00FF00"),
        )

        assertTrue(it.contains(other))
    }

    @Test
    fun contains() {
        val it = BookTags(
                setOf(
                        BookTag("1", "쉬움", "#000000"),
                        BookTag("2", "어려움", "#00FF00"),
                )
        )

        val other = setOf(
                BookTag("2", "어려움", "#00FF00"),
        )

        assertTrue(it.contains(other))
    }
}