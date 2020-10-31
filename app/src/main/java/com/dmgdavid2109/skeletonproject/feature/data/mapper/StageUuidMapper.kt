package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.common.data.Mapper
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import javax.inject.Inject

class StageUuidMapper @Inject constructor() : Mapper<StageResponse, String> {

    override fun map(input: StageResponse): String {
        return input.stages[0].uuid
    }
}
