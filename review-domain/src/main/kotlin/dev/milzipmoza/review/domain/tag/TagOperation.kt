package dev.milzipmoza.review.domain.tag

import dev.milzipmoza.review.domain.tag.model.Tag

interface TagOperation {

    fun save(newTag: Tag): Boolean

    fun update(tag: Tag): Boolean
}