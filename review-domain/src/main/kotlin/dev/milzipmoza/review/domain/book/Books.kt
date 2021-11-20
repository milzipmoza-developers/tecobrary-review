package dev.milzipmoza.review.domain.book

import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.reviewer.BookReviewer

interface Books {

    fun findBy(no: String): Book

    fun findBy(no: String, memberNo: String): Book

    fun findAllBy(category: BookCategory): List<Book>

    fun findAllBy(reviewer: BookReviewer): List<Book>
}