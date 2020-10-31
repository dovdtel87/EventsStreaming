package com.dmgdavid2109.skeletonproject.feature.data.api

import com.dmgdavid2109.skeletonproject.feature.data.model.Event
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import com.dmgdavid2109.skeletonproject.feature.data.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.*

interface HopinApi{
    @POST("users/sso")
    fun retrieveSessionToken(
        @Header("Cookie") cookie : String,
        @Body event: Event,
        @Header("Content-Type") contentType : String = "application/json"
    ): Single<TokenResponse>

    @GET("api/v2/events/{id}/stages")
    fun getStages(
        @Header("Authorization") authorization : String,
        @Path("id") eventId : String
    ) : Single<StageResponse>

    @GET("api/v2/events/{id}/stage/{uuid}/status")
    fun getStageDetail(
        @Header("Authorization") authorization : String,
        @Path("id") eventId: String,
        @Path("uuid") uuid: String
    ) : Single<StageDetailResponse>
}
