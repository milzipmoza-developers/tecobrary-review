package dev.milzipmoza.review.domain.book.model.tag

import dev.milzipmoza.review.domain.book.BookOperationException

class BookTags(
        val tags: Set<BookTag> = setOf()
) {
    fun add(bookTag: Array<out BookTag>): BookTags {
        val mutableTags = tags.toMutableSet()
        mutableTags.addAll(bookTag)
        return BookTags(mutableTags)
    }

    fun remove(bookTag: BookTag): BookTags {
        val mutableTags = tags.toMutableSet()
        val toRemove = mutableTags.find { it.sameAs(bookTag) }
                ?: throw BookOperationException("삭제하려는 태그가 없습니다.")
        mutableTags.remove(toRemove)
        return BookTags(mutableTags)
    }

    fun contains(bookTags: Set<BookTag>): Boolean {
        val intersect = tags.intersect(bookTags)
        return intersect.isNotEmpty()
    }

    fun <R> map(transform: (BookTag) -> R): List<R> {
        return tags.map { transform(it) }
    }
}