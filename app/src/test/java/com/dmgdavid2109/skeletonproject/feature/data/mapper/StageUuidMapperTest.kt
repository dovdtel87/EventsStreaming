package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.feature.data.model.StageDto
import com.dmgdavid2109.skeletonproject.feature.data.model.StageResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object StageUuidMapperTest : Spek({

    val mapper: StageUuidMapper by memoized {
        StageUuidMapper()
    }

    val stageDto1 = StageDto("stage1", "uuid1")
    val stageDto2 = StageDto("stage2", "uuid2")
    val list = listOf<StageDto>(stageDto1, stageDto2)
    val response = StageResponse(list)
    val expectedUuid = "uuid1"

    describe("map") {
        it("then returns the first uuid") {
            val result = mapper.map(response)
            assertEquals(expectedUuid, result)
        }
    }
})
