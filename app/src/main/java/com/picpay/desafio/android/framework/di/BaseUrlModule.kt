package com.picpay.desafio.android.framework.di

import com.picpay.desafio.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}