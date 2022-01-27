package dev.milzipmoza.review.api.endpoint.member.auth

import java.time.LocalDate

data class MemberAuthDto(
        val issuedDate: LocalDate = LocalDate.now(),
        val token: String
)