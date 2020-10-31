package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.feature.data.model.*
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TokenMapperTest : Spek({

    val mapper: TokenMapper by memoized {
        TokenMapper()
    }

    val response = TokenResponse("aToken")
    val expectedToken = "aToken"

    describe("map") {
        it("then returns the token") {
            val result = mapper.map(response)
            assertEquals(expectedToken, result)
        }
    }
})
