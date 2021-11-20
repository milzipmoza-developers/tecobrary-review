package dev.milzipmoza.review.domain.search.model.image

import dev.milzipmoza.review.domain.Url

class SearchBookImage : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}
