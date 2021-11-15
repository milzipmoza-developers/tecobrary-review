package dev.milzipmoza.domain.search.model.image

class SearchBookImage(
        private val host: String,
        private val path: String
) {
    init {
        if (!host.startsWith(HTTPS_HOST)) {
            throw SearchBookImageOperationException("URL 은 $HTTPS_HOST 로 시작할 수 있습니다.")
        }
        if (path.isBlank()) {
            throw SearchBookImageOperationException("URL path 는 비어있을 수 없습니다.")
        }
    }

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
