package com.dmgdavid2109.skeletonproject.feature.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StageDetailResponse(
    val broadcasts: List<BroadcastDto>
)
