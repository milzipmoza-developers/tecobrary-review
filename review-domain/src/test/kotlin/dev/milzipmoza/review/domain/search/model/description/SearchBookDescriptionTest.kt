package dev.milzipmoza.review.domain.search.model.description

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchBookDescriptionTest {

    @Test
    internal fun `글자수를 초과하면 200자로 짤리고 축약 표시가 붙는다`() {
        val contentLength200 = "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890"
        val description = SearchBookDescription(contentLength200 + "1")

        val summarizeContent = description.summarizeContent()

        val suffix = "..."

        assertEquals(contentLength200.length + suffix.length, summarizeContent.length)
        assertTrue(summarizeContent.contains(contentLength200))
        assertTrue(summarizeContent.endsWith(suffix))
    }

    @Test
    internal fun `글자수를 초과하지 않으면 200자가 유지되고 축약표시가 붙지 않는다`() {
        val contentLength200 = "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890"
        val description = SearchBookDescription(contentLength200)

        val summarizeContent = description.summarizeContent()

        val suffix = "..."

        assertEquals(contentLength200.length, summarizeContent.length)
        assertTrue(summarizeContent.contains(contentLength200))
        assertFalse(summarizeContent.endsWith(suffix))
    }
}