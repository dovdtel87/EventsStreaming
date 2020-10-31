package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.TestSchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.StageUuidMapper
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDto
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import com.dmgdavid2109.skeletonproject.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetStagesUseCaseTest : Spek ({

    val repository: StagesRepositoryImpl by mock<StagesRepositoryImpl>()
    val stageUuidMapper: StageUuidMapper by mock<StageUuidMapper>()

    val getStagesUseCase: GetStagesUseCase by memoized {
        GetStagesUseCase(
            repository,
            stageUuidMapper,
            TestSchedulerProvider()
        )
    }

    val stageDto1 = StageDto("stage1", "uuid1")
    val stageDto2 = StageDto("stage2", "uuid2")
    val list = listOf<StageDto>(stageDto1, stageDto2)
    val response = StageResponse(list)

    describe("invoke") {
        beforeEachTest {
            every { repository.getStages("sessioToken","4444") } returns Single.just(response)
            every { stageUuidMapper.map(response) } returns "uuid1"
        }
        it("returns the token ") {
            val testObserver = getStagesUseCase.invoke("sessioToken","4444").test()
            testObserver.assertValue("uuid1")
        }
    }
})
