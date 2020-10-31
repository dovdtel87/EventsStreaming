package com.dmgdavid2109.skeletonproject.feature.data.mapper

import com.dmgdavid2109.skeletonproject.common.data.Mapper
import com.dmgdavid2109.skeletonproject.feature.data.model.TokenResponse
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper<TokenResponse, String> {

    override fun map(input: TokenResponse): String {
        return input.token
    }
}
