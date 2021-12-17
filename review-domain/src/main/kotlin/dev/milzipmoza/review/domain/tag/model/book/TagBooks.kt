package dev.milzipmoza.review.domain.tag.model.book

class TagBooks(
        private val books: MutableList<TagBook> = mutableListOf()
) {

    fun add(tagBook: TagBook): TagBooks {
        if (contains(tagBook)) {
            throw TagBookException("해당 도서에 태그가 이미 추가되어 있습니다.")
        }

        books.add(tagBook)
        return this
    }

    fun remove(tagBook: TagBook): TagBooks {
        if (notContains(tagBook)) {
            throw TagBookException("해당 도서에 태그가 이미 해제되어 있습니다.")
        }

        books.remove(tagBook)
        return this
    }

    fun contains(tagBook: TagBook): Boolean {
        return books.any { it == tagBook }
    }

    private fun notContains(tagBook: TagBook): Boolean {
        return !contains(tagBook)
    }

    fun <R> map(transform: (TagBook) -> R): List<R> {
        return books.map { transform(it) }
    }
}

