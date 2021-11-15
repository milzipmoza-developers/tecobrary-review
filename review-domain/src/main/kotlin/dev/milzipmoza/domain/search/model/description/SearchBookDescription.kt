package dev.milzipmoza.domain.search.model.description

class SearchBookDescription(
        private val content: String
) {

    fun summarizeContent() = removeExceed(this.content)

    private fun removeExceed(content: String) =
            if (content.length > CONTENT_LENGTH) {
                content.removeRange(CONTENT_LENGTH, content.length) + LENGTH_EXCEED_SUFFIX
            } else {
                content
            }

    companion object {
        private const val CONTENT_LENGTH = 200
        private const val LENGTH_EXCEED_SUFFIX = "..."
    }
}
