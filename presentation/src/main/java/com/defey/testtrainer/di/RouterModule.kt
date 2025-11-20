package com.defey.testtrainer.di

import android.content.Context
import com.defey.testtrainer.navigation.AppRouter
import com.defey.testtrainer.navigation.AppRouterImpl
import com.defey.testtrainer.ui.workout.SoundPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindAppRouter(impl: AppRouterImpl): AppRouter

}