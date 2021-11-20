package dev.milzipmoza.review.domain.search.model.image

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SearchBookImageTest {

    @Test
    internal fun `host 가 https 로 시작하지 않는 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            SearchBookImage("grpc://naver.com", "/image/201020120.png")
        }
    }

    @Test
    internal fun `path 길이가 0이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            SearchBookImage("https://naver.com", "")
        }
    }

    @Test
    internal fun `host 가 https 로 시작하고 path 길이가 0이 아니면 정상적으로 생성된다`() {
        val host = "https://naver.com"
        val path = "/image/201020120.png"

        val searchBookImage = SearchBookImage(host, path)

        val toUrl = searchBookImage.toUrl()

        assertTrue(toUrl.startsWith(host))
        assertTrue(toUrl.endsWith(path))
        assertEquals(toUrl, host + path)
    }

    @Test
    internal fun `슬래시 여부가 toUrl 의 결과에 영향을 미치지 않는다`() {

        val searchBookImage1 = SearchBookImage("https://naver.com", "/image/201020120.png")
        val searchBookImage2 = SearchBookImage("https://naver.com/", "image/201020120.png")
        val searchBookImage3 = SearchBookImage("https://naver.com/", "/image/201020120.png")

        assertEquals(searchBookImage1.toUrl(), searchBookImage2.toUrl())
        assertEquals(searchBookImage1.toUrl(), searchBookImage3.toUrl())
    }
}