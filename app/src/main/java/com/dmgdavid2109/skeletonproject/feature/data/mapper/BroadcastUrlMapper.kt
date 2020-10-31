package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.common.data.Mapper
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import javax.inject.Inject

class BroadcastUrlMapper @Inject constructor() : Mapper<StageDetailResponse, String> {

    companion object {
        const val VIDEO_TYPE_MIXER = "mixer"
    }

    override fun map(input: StageDetailResponse) =
        input.broadcasts.filter {
            it.broadcast_type == VIDEO_TYPE_MIXER
        }.take(1)[0].stream_url
}
