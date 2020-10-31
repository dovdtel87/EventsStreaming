package com.dmgdavid2109.skeletonproject.feature.ui

import androidx.fragment.app.Fragment
import com.dmgdavid2109.skeletonproject.di.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    abstract fun bindListFragment(mainFragment: MainFragment): Fragment
}
