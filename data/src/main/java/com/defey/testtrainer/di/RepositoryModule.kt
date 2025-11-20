package com.defey.testtrainer.di

import com.defey.testtrainer.network.ApiResponseHandler
import com.defey.testtrainer.repository.WorkoutRepository
import com.defey.testtrainer.repository.WorkoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    companion object {
        @Provides
        @Singleton
        fun provideApiResponseHandler(): ApiResponseHandler {
            return ApiResponseHandler()
        }
    }

    @Binds
    @Singleton
    fun bindWorkoutRepository(impl: WorkoutRepositoryImpl): WorkoutRepository
}