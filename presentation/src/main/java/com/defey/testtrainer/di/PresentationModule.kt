package com.defey.testtrainer.di

import android.content.Context
import com.defey.testtrainer.ui.workout.SoundPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {
    @Provides
    @Singleton
    fun provideApiResponseHandler(@ApplicationContext context: Context): SoundPlayer {
        return SoundPlayer(context)
    }
}