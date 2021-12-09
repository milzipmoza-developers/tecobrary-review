package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.*
import dev.milzipmoza.review.mongo.mark.mongo.*
import org.springframework.stereotype.Repository

@Repository
class MongoMarks(
        private val mongoMarkRepository: MongoMarkRepository,
        private val mongoMarkedRepository: MongoMarkedRepository
) : Marks {

    override fun findByMemberAndBookAndType(member: MarkMember, book: MarkBook, type: MarkType): Mark? {
        val documentMark = mongoMarkRepository.findByMemberAndBookAndType(DocumentMarkMapper.map(member), DocumentMarkMapper.map(book), type.toString())
                ?: return null

        val documentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId)
                .orElse(null)
                ?: return DocumentMarkMapper.map(documentMark)

        return DocumentMarkMapper.map(documentMark, documentMarked)
    }

    override fun findAllByMemberAndBook(member: MarkMember, book: MarkBook): List<Mark> {
        val documentMarks = mongoMarkRepository.findAllByMemberAndBook(DocumentMarkMapper.map(member), DocumentMarkMapper.map(book))

        val documentMarkeds = mongoMarkedRepository.findAllById(documentMarks.map { it.markedObjectId }).toList()

        return documentMarks.map { DocumentMarkMapper.map(it, documentMarkeds) }
    }
}