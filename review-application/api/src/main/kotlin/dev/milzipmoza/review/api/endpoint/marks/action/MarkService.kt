package dev.milzipmoza.review.api.endpoint.marks.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.mark.*

@ApplicationService
class MarkService(
        private val marks: Marks,
        private val markOperation: MarkOperation
) {

    fun doMark(bookNo: String, memberNo: String, type: String): Boolean {
        val mark = marks.findBy(MarkMember(memberNo), MarkBook(bookNo), MarkType.valueOfIgnoreCases(type))

        return when {
            mark == null -> {
                markOperation.create(Mark(type, memberNo, bookNo))
                true
            }
            mark.marked -> true
            else -> {
                markOperation.update(mark.no, mark.mark())
                true
            }
        }
    }
}