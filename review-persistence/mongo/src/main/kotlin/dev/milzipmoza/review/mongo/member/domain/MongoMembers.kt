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
                ?: throw DocumentNotFoundException("해당하는 회원을 찾을 수 없어요.")

        return DocumentMemberMapper.map(documentMember)
    }

    override fun findAllIn(nos: List<String>): List<Member> {
        val ids = nos.map { ObjectId(it) }
        return mongoMemberRepository.findAllByIdIn(ids)
                .map { DocumentMemberMapper.map(it) }
    }

    override fun isExist(no: String): Boolean {
        return mongoMemberRepository.existsById(ObjectId(no))
    }
}