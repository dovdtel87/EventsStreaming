package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.SchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.StageUuidMapper
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

class GetStagesUseCase @Inject constructor(
    private val stagesRepository: StagesRepositoryImpl,
    private val stageUuidMapper: StageUuidMapper,
    private val schedulerProvider: SchedulerProvider
) {

    fun invoke(sessionToken: String, eventId: String) : Single<String> {
        return stagesRepository.getStages(sessionToken, eventId)
            .map(stageUuidMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
