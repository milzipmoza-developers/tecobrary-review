package dev.milzipmoza.review.domain.book.model.detail

import dev.milzipmoza.review.domain.Url

class BookImageUrl : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}