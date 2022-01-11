package dev.milzipmoza.review.mongo.member.domain

import dev.milzipmoza.review.domain.member.MemberOperation
import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.mongo.extension.Logger
import dev.milzipmoza.review.mongo.member.mongo.DocumentMemberMapper
import dev.milzipmoza.review.mongo.member.mongo.MongoMemberRepository
import org.springframework.stereotype.Repository

@Repository
class MongoMemberOperation(
        private val mongoMemberRepository: MongoMemberRepository
) : MemberOperation {

    private val log = Logger.of(this)

    override fun upsert(member: Member): Boolean {
        val documentMemberAccount = DocumentMemberMapper.map(member.account)
        val foundMember = mongoMemberRepository.findByAccount(documentMemberAccount)

        if (foundMember == null) {
            val newDocumentMember = DocumentMemberMapper.map(member)
            mongoMemberRepository.save(newDocumentMember)
            log.info("[MongoMemberOperation][{}] created new member={}", newDocumentMember.id, newDocumentMember)
            return true
        }

        foundMember.info.name = member.info.name
        foundMember.info.description = member.info.description
        mongoMemberRepository.save(foundMember)
        log.info("[MongoMemberOperation][{}] updated member={}", foundMember.id, foundMember)
        return true
    }
}