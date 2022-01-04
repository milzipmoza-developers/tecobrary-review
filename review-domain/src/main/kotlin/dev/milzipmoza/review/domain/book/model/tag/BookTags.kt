package dev.milzipmoza.review.domain.book.model.tag

import dev.milzipmoza.review.domain.book.BookOperationException

class BookTags(
        val tags: List<BookTag> = listOf()
) {
    fun add(bookTag: Array<out BookTag>): BookTags {
        val mutableTags = tags.toMutableList()
        mutableTags.addAll(bookTag)
        return BookTags(mutableTags)
    }

    fun remove(bookTag: BookTag): BookTags {
        val mutableTags = tags.toMutableList()
        val toRemove = mutableTags.find { it.sameAs(bookTag) }
                ?: throw BookOperationException("삭제하려는 태그가 없습니다.")
        mutableTags.remove(toRemove)
        return BookTags(mutableTags)
    }

    fun <R> map(transform: (BookTag) -> R): List<R> {
        return tags.map { transform(it) }
    }
}