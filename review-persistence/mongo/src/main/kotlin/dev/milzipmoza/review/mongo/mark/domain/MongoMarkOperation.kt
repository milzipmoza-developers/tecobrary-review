package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkOperation
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMapper
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoMarkOperation(
        private val mongoMarkRepository: MongoMarkRepository,
        private val mongoMarkedRepository: MongoMarkedRepository
) : MarkOperation {

    override fun create(mark: Mark): Boolean {
        val (documentMark, documentMarked) = DocumentMarkMapper.map(mark)
        mongoMarkedRepository.save(documentMarked)
        mongoMarkRepository.save(documentMark)
        return true
    }

    override fun update(no: String, mark: Mark): Boolean {
        val documentMark = mongoMarkRepository.findById(ObjectId(no)).unwrap()
                ?: return false

        val documentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId).unwrap()

        return when(documentMarked == null) {
            true -> {
                val (_, missingDocumentMarked) = DocumentMarkMapper.map(mark)
                mongoMarkedRepository.save(missingDocumentMarked)
                true
            }
            false -> {
                documentMarked.apply { marked = mark.marked }
                mongoMarkedRepository.save(documentMarked)
                true
            }
        }
    }
}