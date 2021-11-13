package dev.milzipmoza.domain.book.mark

class BookMarks(
        private val marks: MutableList<BookMark> = mutableListOf()
) {

    fun mark(bookMark: BookMark): Boolean {
        val hasAlready = marks.any { it.sameAs(bookMark) }

        if (hasAlready) {
            throw BookMarkOperationException("이미 ${bookMark.type.displayName} 하셨습니다.")
        }

        return marks.add(bookMark)
    }

    fun unmark(bookMark: BookMark): Boolean {
        if (marks.isEmpty()) {
            throw BookMarkOperationException("이미 ${bookMark.type.displayName}를 해제하셨습니다.")
        }

        val notHas = marks.none { it.sameAs(bookMark) }

        if (notHas) {
            throw BookMarkOperationException("이미 ${bookMark.type.displayName}를 해제하셨습니다.")
        }

        return marks.remove(bookMark)
    }
}
