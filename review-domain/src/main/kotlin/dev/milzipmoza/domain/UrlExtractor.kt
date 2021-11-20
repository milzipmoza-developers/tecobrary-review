package dev.milzipmoza.domain

object UrlExtractor {

    private const val HTTPS_HOST = "https://"
    private const val SLASH = "/"
    private const val INDEX_OF_START = 0
    private const val INCLUSIVE_LAST_INDEX = 1

    fun extractHost(fullUrl: String): String {
        val (protocolRemovedUrl, firstSlash) = prepareExtract(fullUrl)

        val host = protocolRemovedUrl.substring(INDEX_OF_START, firstSlash + INCLUSIVE_LAST_INDEX)

        return "$HTTPS_HOST$host"
    }

    fun extractPath(fullUrl: String): String {
        val (protocolRemovedUrl, firstSlash) = prepareExtract(fullUrl)

        val path = protocolRemovedUrl.substring(firstSlash + 1)

        validateExtractPath(path)

        return "$SLASH$path"
    }

    private fun prepareExtract(fullUrl: String): Pair<String, Int> {
        if (!fullUrl.startsWith(HTTPS_HOST)) {
            throw IllegalArgumentException("URL 이 https 로 시작하는지 확인해주세요.")
        }

        val protocolRemovedUrl = fullUrl.removePrefix(HTTPS_HOST)

        val firstSlash = protocolRemovedUrl.indexOf(SLASH)

        if (firstSlash.notExist()) {
            throw IllegalArgumentException("정확한 URL 인지 확인해주세요.")
        }
        return Pair(protocolRemovedUrl, firstSlash)
    }

    private fun validateExtractPath(result: String) {
        if (result.isBlank() || result == SLASH) {
            throw IllegalArgumentException("정확한 URL 인지 확인해주세요.")
        }
    }

    private fun Int.notExist() = this == -1
}