package dev.milzipmoza.review.api.endpoint.member

enum class UrlStatus {
    SUCCESS(),
    REQUIRE_RE_AUTH(),
    NOT_MATCH_DEVICE(),
    AUTH_FAILURE()
}