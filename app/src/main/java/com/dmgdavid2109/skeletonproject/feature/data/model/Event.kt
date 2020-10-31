package com.dmgdavid2109.skeletonproject.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event(
    @Json(name = "event_slug") val event_slug: String = "david-s-event"
)
