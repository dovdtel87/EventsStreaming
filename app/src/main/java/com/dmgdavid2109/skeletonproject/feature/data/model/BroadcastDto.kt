package com.dmgdavid2109.skeletonproject.feature.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BroadcastDto(
    val stream_url: String,
    val broadcast_type: String,
    val status: String
)
