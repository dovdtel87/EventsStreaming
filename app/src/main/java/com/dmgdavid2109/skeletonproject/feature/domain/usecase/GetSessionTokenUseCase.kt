package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.SchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.TokenMapper
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

class GetSessionTokenUseCase @Inject constructor(
    private val stagesRepository: StagesRepositoryImpl,
    private val tokenMapper: TokenMapper,
    private val schedulerProvider: SchedulerProvider
) {

    fun invoke(userToken : String) : Single<String> {
        return stagesRepository.retrieveSessionToken(userToken)
            .map(tokenMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
