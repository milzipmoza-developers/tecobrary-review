package dev.milzipmoza.review.mongo.member.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMemberRepository : MongoRepository<DocumentMember, ObjectId> {

    fun findByAccount(account: DocumentMemberAccount): DocumentMember?
}

@Document(collection = "members")
data class DocumentMember(
        @Id val id: ObjectId = ObjectId.get(),
        @Indexed(unique = true) val account: DocumentMemberAccount,
        val info: DocumentMemberInfo,
        val isAdmin: Boolean? = null
)

data class DocumentMemberAccount(
        val identifier: String,
        val provider: String
)

data class DocumentMemberInfo(
        var name: String,
        var profileImageUrl: String,
        val email: String,
        var description: String,
)

