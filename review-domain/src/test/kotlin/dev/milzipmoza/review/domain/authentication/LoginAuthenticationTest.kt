package dev.milzipmoza.review.domain.authentication

import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.member.model.MemberAccount
import dev.milzipmoza.review.domain.member.model.MemberInfo
import dev.milzipmoza.review.domain.member.model.MemberOAuthProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDateTime

class LoginAuthenticationTest : StringSpec({

    "authentication 이 null 이면 새로운 Authentication 을 생성한다." {
        val member = Member(
                no = "",
                account = MemberAccount(identifier = "", provider = MemberOAuthProvider.GITHUB),
                info = MemberInfo(name = "", email = "", blogUrl = "", description = ""),
        )

        val authentication: Authentication? = null

        val login = LoginAuthentication(member, authentication)

        val loginAuthentication = login.login("device_id", "accessToken")

        loginAuthentication.lastLoginDateTime shouldBe loginAuthentication.createdDateTime
    }

    "authentication 이 null 이 아니면 lastLoginDateTime 이 갱신된다." {
        val now = LocalDateTime.now()

        val member = Member(
                no = "",
                account = MemberAccount(identifier = "", provider = MemberOAuthProvider.GITHUB),
                info = MemberInfo(name = "", email = "", blogUrl = "", description = ""),
        )

        val authentication = Authentication(
                code = "",
                accessToken = "",
                deviceId = "device_id",
                memberNo = "",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        val login = LoginAuthentication(member, authentication)

        val loginAuthentication = login.login("device_id", "accessToken")

        loginAuthentication.lastLoginDateTime shouldNotBe loginAuthentication.createdDateTime
    }

    "device 정보가 다르면 예외가 발생한다.." {
        val now = LocalDateTime.now()

        val member = Member(
                no = "",
                account = MemberAccount(identifier = "", provider = MemberOAuthProvider.GITHUB),
                info = MemberInfo(name = "", email = "", blogUrl = "", description = ""),
        )

        val authentication = Authentication(
                code = "",
                accessToken = "",
                deviceId = "device_id",
                memberNo = "",
                createdDateTime = now,
                lastLoginDateTime = now
        )

        shouldThrow<IllegalAccessException> {
            val loginAuthentication = LoginAuthentication(member, authentication)

            loginAuthentication.login("device_id1", "accessToken")
        }
    }
})
