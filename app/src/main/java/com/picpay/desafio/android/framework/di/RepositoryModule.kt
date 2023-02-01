package com.picpay.desafio.android.framework.di

import com.picpay.desafio.android.business.data.repository.UsersRemoteDataSource
import com.picpay.desafio.android.business.data.repository.UsersRepository
import com.picpay.desafio.android.framework.UsersRepositoryImpl
import com.picpay.desafio.android.framework.network.response.UserResponse
import com.picpay.desafio.android.framework.remote.RetrofitUsersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUsersRepository(repository: UsersRepositoryImpl) : UsersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitUsersDataSource
    ) : UsersRemoteDataSource<MutableList<UserResponse>>
}