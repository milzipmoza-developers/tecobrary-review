package dev.milzipmoza.review.mongo

class DocumentNotFoundException : RuntimeException {

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}