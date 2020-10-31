package com.dmgdavid2109.skeletonproject.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @field:Json(name = "token") val token: String
)
