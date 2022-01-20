package dev.milzipmoza.review.api.endpoint.member

enum class UrlStatus {
    SUCCESS(),
    AUTH_EXPIRED(),
    NOT_MATCH_DEVICE(),
    AUTH_FAILURE()
}