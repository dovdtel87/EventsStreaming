package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.feature.data.model.BroadcastDto
import com.dmgdavid2109.skeletonproject.feature.data.model.StageDetailResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BroadcastUrlMapperTest : Spek({

    val mapper: BroadcastUrlMapper by memoized {
        BroadcastUrlMapper()
    }

    val broadCastDto1 = BroadcastDto("mixURL", "mixer", "active")
    val broadCastDto2 = BroadcastDto("rtmpURL", "rtmp", "active")
    val broadCastDto3 = BroadcastDto("otherUrl", "otherUrl", "active")
    val list = listOf<BroadcastDto>(broadCastDto1, broadCastDto2, broadCastDto3)
    val response = StageDetailResponse(list)
    val expectedUrl = "mixURL"

    describe("map") {
        it("then maps to correct broadcast URL") {
            val result = mapper.map(response)
            assertEquals(broadCastDto1, result)
        }
    }
})
