package dev.milzipmoza.domain.book

import dev.milzipmoza.domain.book.category.BookCategory
import dev.milzipmoza.domain.book.reviewer.BookReviewer

interface Books {

    fun findBy(no: String): Book

    fun findBy(no: String, memberNo: String): Book

    fun findAllBy(category: BookCategory): List<Book>

    fun findAllBy(reviewer: BookReviewer): List<Book>
}