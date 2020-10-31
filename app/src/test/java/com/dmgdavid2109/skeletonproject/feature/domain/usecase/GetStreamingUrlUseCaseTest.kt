package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.TestSchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.BroadcastUrlMapper
import com.dmgdavid2109.skeletonproject.feature.data.model.BroadcastDto
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import com.dmgdavid2109.skeletonproject.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetStreamingUrlUseCaseTest : Spek({
    val repository: StagesRepositoryImpl by mock<StagesRepositoryImpl>()
    val broadcastUrlMapper: BroadcastUrlMapper by mock<BroadcastUrlMapper>()

    val token = "aToken"
    val broadCastDto1 = BroadcastDto("mixURL", "mixer", "active")
    val broadCastDto2 = BroadcastDto("rtmpURL", "rtmp", "active")
    val broadCastDto3 = BroadcastDto("otherUrl", "otherUrl", "active")
    val list = listOf<BroadcastDto>(broadCastDto1, broadCastDto2, broadCastDto3)
    val response = StageDetailResponse(list)

    val getStreamingUrlUseCase: GetStreamingUrlUseCase by memoized {
        GetStreamingUrlUseCase(
            repository,
            broadcastUrlMapper,
            TestSchedulerProvider()
        )
    }

    describe("invoke") {
        beforeEachTest {
            every { repository.getStageDetail ("sessionToken", "eventId", "uuod") } returns Single.just(response)
            every { broadcastUrlMapper.map(any()) } returns broadCastDto1
        }
        it("returns the url") {
            val testObserver = getStreamingUrlUseCase.invoke("sessionToken", "eventId", "uuod").test()
            testObserver.assertValue(broadCastDto1)
        }
    }
})
