package dev.milzipmoza.review.domain.book

import dev.milzipmoza.review.domain.book.model.Book

interface BookOperation {

    fun save(book: Book)

    fun edit(book: Book)
}