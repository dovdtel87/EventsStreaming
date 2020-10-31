package com.dmgdavid2109.skeletonproject.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
