package dev.milzipmoza.review.domain.category.model.url

import dev.milzipmoza.review.domain.Url

class CategoryImageUrl : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}