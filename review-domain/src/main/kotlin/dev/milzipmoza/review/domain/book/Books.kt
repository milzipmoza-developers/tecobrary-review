package dev.milzipmoza.review.domain.book

import dev.milzipmoza.review.domain.book.model.Book

interface Books {

    fun findBy(isbn: String): Book
}