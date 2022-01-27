package com.milzipmoza.review.util

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date


fun LocalDateTime.convert(): Date {
    return Date.from(this.toInstant(ZoneOffset.of("+09:00")))
}