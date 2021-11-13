package dev.milzipmoza.domain.book

interface BookOperation {

    fun save(book: Book)

    fun edit(book: Book)
}