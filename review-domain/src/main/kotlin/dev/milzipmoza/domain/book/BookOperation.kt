package dev.milzipmoza.domain.book

import dev.milzipmoza.domain.book.model.Book

interface BookOperation {

    fun save(book: Book)

    fun edit(book: Book)
}