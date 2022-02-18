package dev.milzipmoza.review.utils

import dev.milzipmoza.review.mongo.book.mongo.DocumentBook

object MessageContentUtils {

    fun multipleBooks(documentBooks: List<DocumentBook>): String {
        val header = "[다건 도서 등록 알림]"
        val body = documentBooks.map {
            """
            * ISBN - ${it.isbn}
            * 제목 - ${it.detail.title}
        """.trimIndent()
        }.joinToString("\n")
        val footer = "도서 검색시 등록되었습니다."
        return listOf(header, body, footer).joinToString("\n\n")
    }
}