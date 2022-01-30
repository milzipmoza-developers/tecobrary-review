package dev.milzipmoza.review.jwt

class InvalidTokenException : RuntimeException{

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}