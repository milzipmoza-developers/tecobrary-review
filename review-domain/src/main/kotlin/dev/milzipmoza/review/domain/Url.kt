package dev.milzipmoza.review.domain

open class Url{

    val host: String
    val path: String

    constructor(host: String, path: String) {
        if (!host.startsWith(HTTPS_HOST)) {
            throw IllegalArgumentException("URL 은 $HTTPS_HOST 로 시작할 수 있습니다.")
        }
        if (path.isBlank()) {
            throw IllegalArgumentException("URL path 는 비어있을 수 없습니다.")
        }
        this.host = host
        this.path = path
    }

    constructor(fullUrl: String) : this(UrlExtractor.extractHost(fullUrl), UrlExtractor.extractPath(fullUrl))

    fun toUrl() = when {
        host.endsWith(SLASH) && path.startsWith(SLASH) -> "${host.removeSuffix(SLASH)}$path"
        host.endsWith(SLASH) || path.startsWith(SLASH) -> "$host$path"
        else -> "$host/$path"
    }

    companion object {
        private const val HTTPS_HOST = "https://"
        private const val SLASH = "/"
    }

}
