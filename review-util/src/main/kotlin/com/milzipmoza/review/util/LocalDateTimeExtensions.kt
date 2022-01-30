package com.milzipmoza.review.util

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date


fun LocalDateTime.convert(): Date {
    return Date.from(this.toInstant(ZoneOffset.of("+09:00")))
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")

fun String.convert(): LocalDateTime {
    return LocalDateTime.parse(this, formatter)
}