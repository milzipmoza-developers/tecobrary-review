package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkOperation
import dev.milzipmoza.review.domain.mark.MarkOperationException
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMapper
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMember
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoMarkOperation(
        private val mongoMarkRepository: MongoMarkRepository,
        private val mongoMarkedRepository: MongoMarkedRepository
) : MarkOperation {

    override fun create(mark: Mark) {
        val (documentMark, documentMarked) = DocumentMarkMapper.map(mark)
        mongoMarkedRepository.save(documentMarked)
        mongoMarkRepository.save(documentMark)
    }

    override fun update(no: String, mark: Mark) {
        val optionalDocumentMark = mongoMarkRepository.findById(ObjectId(no))

        if (optionalDocumentMark.isEmpty) {
            return
        }

        val documentMark = optionalDocumentMark.get()

        val optionalDocumentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId)

        if (optionalDocumentMarked.isEmpty) {
            return
        }

        val documentMarked = optionalDocumentMarked.get()

        documentMarked.marked = mark.marked

        mongoMarkedRepository.save(documentMarked)
    }
}