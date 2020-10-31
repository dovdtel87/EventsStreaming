package com.dmgdavid2109.skeletonproject.feature.domain.usecase

import com.dmgdavid2109.skeletonproject.common.network.TestSchedulerProvider
import com.dmgdavid2109.skeletonproject.feature.data.mapper.TokenMapper
import com.dmgdavid2109.skeletonproject.feature.data.model.TokenResponse
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import com.dmgdavid2109.skeletonproject.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetSessionTokenUseCaseTest : Spek ({

    val repository: StagesRepositoryImpl by mock<StagesRepositoryImpl>()
    val tokenMapper: TokenMapper by mock<TokenMapper>()

    val token = "aToken"
    val tokenResponse = TokenResponse(token)

    val getSessionTokenUseCase: GetSessionTokenUseCase by memoized {
        GetSessionTokenUseCase(
            repository,
            tokenMapper,
            TestSchedulerProvider()
        )
    }

    describe("invoke") {
        beforeEachTest {
            every { repository.retrieveSessionToken("userToken") } returns Single.just(tokenResponse)
            every { tokenMapper.map(any()) } returns token
        }
        it("returns the token ") {
            val testObserver = getSessionTokenUseCase.invoke("userToken").test()
            testObserver.assertValue(token)
        }
    }
})
