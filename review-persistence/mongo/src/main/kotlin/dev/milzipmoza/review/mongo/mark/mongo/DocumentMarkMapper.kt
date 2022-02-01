package dev.milzipmoza.review.mongo.mark.mongo

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import org.bson.types.ObjectId
import java.time.LocalDateTime

object DocumentMarkMapper {

    fun map(mark: Mark): Pair<DocumentMark, DocumentMarked> {
        val documentMarked = DocumentMarked(
                marked = mark.marked,
                txDateTime = LocalDateTime.now()
        )
        val documentMark = DocumentMark(
                type = mark.type.toString(),
                member = map(mark.member),
                book = map(mark.book),
                markedObjectId = documentMarked.id
        )

        return Pair(documentMark, documentMarked)
    }

    fun map(markMember: MarkMember) = DocumentMarkMember(no = markMember.no)

    fun map(markBook: MarkBook) = DocumentMarkBook(no = markBook.no)

    fun map(member: DocumentMarkMember) = MarkMember(member.no)

    fun map(book: DocumentMarkBook) = MarkBook(book.no)

    fun map(documentMark: DocumentMark) = Mark(
            no = documentMark.id.toHexString(),
            type = MarkType.valueOf(documentMark.type),
            marked = false,
            member = map(documentMark.member),
            book = map(documentMark.book)
    )

    fun map(documentMark: DocumentMark, documentMarked: DocumentMarked?) = Mark(
            no = documentMark.id.toHexString(),
            type = MarkType.valueOf(documentMark.type),
            marked = documentMarked?.marked ?: false,
            member = map(documentMark.member),
            book = map(documentMark.book)
    )

    fun map(documentMark: DocumentMark, documentMarkeds: List<DocumentMarked>) = Mark(
            no = documentMark.id.toHexString(),
            type = MarkType.valueOf(documentMark.type),
            marked = map(documentMarkeds, documentMark.markedObjectId),
            member = map(documentMark.member),
            book = map(documentMark.book))

    private fun map(documentMarkeds: List<DocumentMarked>, markedObjectId: ObjectId) =
            (documentMarkeds.find { marked -> marked.id == markedObjectId }
                    ?.marked
                    ?: false)
}