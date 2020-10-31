package com.dmgdavid2109.skeletonproject.feature.data.repository

import com.dmgdavid2109.skeletonproject.feature.data.api.HopinApi
import com.dmgdavid2109.skeletonproject.feature.data.model.Event
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.TokenResponse
import com.dmgdavid2109.skeletonproject.feature.domain.repository.StagesRepository
import io.reactivex.Single
import javax.inject.Inject

class StagesRepositoryImpl @Inject constructor(
    private val api: HopinApi
): StagesRepository {

    override fun retrieveSessionToken(userToken : String) : Single<TokenResponse> {
        return api.retrieveSessionToken(userToken.formattedUserToken(), Event())
    }

    override fun getStages(sessionToken: String, eventId: String): Single<StageResponse> {
        return api.getStages(sessionToken.bearerToken(), eventId)
    }

    override fun getStageDetail(
        sessionToken: String,
        eventId: String,
        uuid: String
    ): Single<StageDetailResponse> {
        return api.getStageDetail(sessionToken.bearerToken(), eventId, uuid)
    }

    private fun String.bearerToken() = "Bearer $this"
    private fun String.formattedUserToken() = "user.token=$this"
}
