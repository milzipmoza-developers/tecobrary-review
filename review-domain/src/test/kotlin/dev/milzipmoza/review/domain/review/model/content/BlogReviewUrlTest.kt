package dev.milzipmoza.review.domain.review.model.content

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BlogReviewUrlTest {

    @Test
    fun `fullUrl 을 이용해 BlogReviewUrl 을 생성한다`() {
        val fullUrl = "https://naver.com/img/path.to"

        val blogReviewUrl = BlogReviewUrl(fullUrl)

        assertEquals(blogReviewUrl.toUrl(), fullUrl)
    }

    @Test
    fun `host 와 path 를 분리하여 BlogReviewUrl 을 생성한다`() {
        val fullUrl = "https://naver.com/img/path.to"

        val blogReviewUrl = BlogReviewUrl(fullUrl)

        assertEquals(blogReviewUrl.toUrl(), fullUrl)
    }
}