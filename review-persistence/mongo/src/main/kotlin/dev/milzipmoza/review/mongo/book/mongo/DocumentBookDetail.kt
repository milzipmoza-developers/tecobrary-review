package dev.milzipmoza.review.mongo.book.mongo

import java.time.LocalDate
import javax.print.Doc
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoBookDetailRepository : MongoRepository<DocumentBookDetail, ObjectId> {

    fun findAllByIdIn(id: Iterable<ObjectId>): List<DocumentBookDetail>
}

@Document(collection = "book_details")
data class DocumentBookDetail(
        val id: ObjectId = ObjectId(),
        val title: String,
        val publisher: String,
        val author: String,
        val image: DocumentBookDetailImage,
        val locale: String,
        val publishDate: LocalDate,
        val description: String
)

class DocumentBookDetailImage(
        val host: String,
        val path: String
)