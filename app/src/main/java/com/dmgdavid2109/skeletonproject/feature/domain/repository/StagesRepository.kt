package com.dmgdavid2109.skeletonproject.feature.domain.repository

import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.TokenResponse
import io.reactivex.Single

interface StagesRepository {
    fun retrieveSessionToken(userToken: String) : Single<TokenResponse>
    fun getStages(sessionToken: String, eventId: String) : Single<StageResponse>
    fun getStageDetail(sessionToken: String, eventId: String, uuid: String) : Single<StageDetailResponse>
}
