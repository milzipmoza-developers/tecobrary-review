package dev.milzipmoza.review.mongo.member.domain

import dev.milzipmoza.review.domain.member.Members
import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.member.mongo.DocumentMemberMapper
import dev.milzipmoza.review.mongo.member.mongo.MongoMemberRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoMembers(
        private val mongoMemberRepository: MongoMemberRepository
) : Members {

    override fun findBy(no: String): Member {
        val documentMember = mongoMemberRepository.findById(ObjectId(no)).unwrap()
                ?: throw DocumentNotFoundException("해당하는 회원을 찾을 수 없습니다.")

        return DocumentMemberMapper.map(documentMember)
    }
}