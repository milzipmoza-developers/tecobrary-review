package dev.milzipmoza.review.config.filter

data class ControllerLog(
        val path: String,
        val method: String
) {
    private val startMillisecond = System.currentTimeMillis()

    private var statusCode: Int? = null
    private var elapsed: Long? = null

    fun response(statusCode: Int) {
        this.statusCode = statusCode
        this.elapsed = System.currentTimeMillis() - startMillisecond
    }

    fun logMessage(): String {
        return "[ControllerLog] method=$method, path=$path, statusCode=$statusCode, elapsed=${elapsed}"
    }
}
