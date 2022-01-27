package dev.milzipmoza.review.api.endpoint.authentication

enum class UrlStatus {
    SUCCESS(),
    REQUIRE_RE_AUTH(),
    NOT_MATCH_DEVICE(),
    AUTH_FAILURE()
}