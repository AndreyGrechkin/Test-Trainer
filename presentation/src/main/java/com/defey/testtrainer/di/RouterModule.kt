package com.defey.testtrainer.di

import com.defey.testtrainer.navigation.AppRouter
import com.defey.testtrainer.navigation.AppRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindAppRouter(impl: AppRouterImpl): AppRouter

}