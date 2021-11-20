package dev.milzipmoza.review.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UrlExtractorTest {

    @Test
    fun `정상적인 URL 에서 host 를 추출한다`() {
        val fullUrl = "https://www.naver.com/image/path"

        val host = UrlExtractor.extractHost(fullUrl)

        assertEquals("https://www.naver.com/", host)
    }

    @Test
    fun `host 를 추출할 때 path 가 없으면 예외가 발생한다`() {
        val fullUrl = "https://www.naver.com"

        assertThrows<IllegalArgumentException> {
            UrlExtractor.extractHost(fullUrl)
        }
    }

    @Test
    fun `host 를 추출할 때 지원하지 않는 프로토콜이면 예외가 발생한다`() {
        val fullUrl = "grpc://www.naver.com/"

        assertThrows<IllegalArgumentException> {
            UrlExtractor.extractHost(fullUrl)
        }
    }

    @Test
    fun `정상적인 URL 에서 path 를 추출한다`() {
        val fullUrl = "https://www.naver.com/image/path.png"

        val host = UrlExtractor.extractPath(fullUrl)

        assertEquals("/image/path.png", host)
    }

    @Test
    fun `path 를 추출할 때 path 가 없으면 예외가 발생한다`() {
        val fullUrl = "https://www.naver.com/"

        assertThrows<IllegalArgumentException> {
            UrlExtractor.extractPath(fullUrl)
        }
    }
}