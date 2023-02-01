package com.picpay.desafio.android.framework.di

import com.picpay.desafio.android.business.usecase.GetUsersUseCase
import com.picpay.desafio.android.business.usecase.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindUsersUseCase(useCase: GetUsersUseCaseImpl) : GetUsersUseCase
}