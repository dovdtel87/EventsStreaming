package com.dmgdavid2109.skeletonproject.common.ui

fun <T : Any> T.toEvent(): Event<T> {
    return Event(this)
}
