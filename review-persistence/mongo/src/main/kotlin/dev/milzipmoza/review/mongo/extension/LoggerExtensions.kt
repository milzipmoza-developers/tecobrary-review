package dev.milzipmoza.review.mongo.extension

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.helpers.Util

object Logger {
    fun of(clazz: Any): Logger = LoggerFactory.getLogger(clazz.javaClass)
}
