package dev.milzipmoza.review.domain.book.model.category

class BookCategories(
        private val categories: MutableList<BookCategory> = mutableListOf()
) {

    internal fun add(category: BookCategory): Boolean {
        val hasAlready = categories.any { it.sameAs(category) }

        if (hasAlready) {
            throw BookCategoryOperationException("이미 추가된 카테고리입니다.")
        }

        categories.add(category)
        return true
    }

    internal fun remove(category: BookCategory): Boolean {
        if (categories.isEmpty()) {
            throw BookCategoryOperationException("제거할 카테고리가 없습니다.")
        }

        val notHasCategory = categories.none { it.sameAs(category) }

        if (notHasCategory) {
            throw BookCategoryOperationException("제거하려는 카테고리가 없습니다.")
        }

        categories.remove(category)
        return true
    }
}
