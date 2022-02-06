package dev.milzipmoza.review.api.endpoint.marks.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.mark.*

@ApplicationService
class UnmarkService(
        private val marks: Marks,
        private val markOperation: MarkOperation
) {

    fun doUnmark(bookNo: String, memberNo: String, type: String): Boolean {
        val mark = marks.findBy(MarkMember(memberNo), MarkBook(bookNo), MarkType.valueOfIgnoreCases(type))
                ?: return true

        if (mark.unmarked) {
            return true
        }

        markOperation.update(mark.no, mark.unmark())
        return true
    }
}