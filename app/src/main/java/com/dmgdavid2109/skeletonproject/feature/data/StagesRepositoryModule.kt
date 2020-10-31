package com.dmgdavid2109.skeletonproject.feature.data

import com.dmgdavid2109.skeletonproject.feature.data.api.HopinApi
import com.dmgdavid2109.skeletonproject.feature.domain.repository.StagesRepository
import com.dmgdavid2109.skeletonproject.feature.data.repository.StagesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
abstract class StagesRepositoryModule {

    @Binds
    @ActivityScoped
    internal abstract fun tokensRepository(repository: StagesRepositoryImpl): StagesRepository

    companion object {
        @Provides
        @ActivityScoped
        internal fun provideApi(
            retrofit: Retrofit
        ): HopinApi {
            return retrofit.create(HopinApi::class.java)
        }
    }
}
