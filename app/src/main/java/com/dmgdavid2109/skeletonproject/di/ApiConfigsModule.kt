package com.dmgdavid2109.skeletonproject.di

import com.dmgdavid2109.skeletonproject.common.network.AppSchedulerProvider
import com.dmgdavid2109.skeletonproject.common.network.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return "https://staging.hopin.to/"
    }

    @Provides
    @Singleton
    fun providesSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
