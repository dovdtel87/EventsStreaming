package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.SchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.BroadcastUrlMapper
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

class GetStreamingUrlUseCase @Inject constructor(
    private val stagesRepository: StagesRepositoryImpl,
    private val stageUuidMapper: BroadcastUrlMapper,
    private val schedulerProvider: SchedulerProvider
) {

    fun invoke(sessionToken: String, eventId: String, uuid: String) : Single<String> {
        return stagesRepository.getStageDetail(sessionToken, eventId, uuid)
            .map(stageUuidMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
