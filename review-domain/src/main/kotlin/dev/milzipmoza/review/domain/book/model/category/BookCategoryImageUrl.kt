package dev.milzipmoza.review.domain.book.model.category

import dev.milzipmoza.review.domain.Url


class BookCategoryImageUrl : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}