package com.dmgdavid2109.skeletonproject.common.network

import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}
