package com.picpay.desafio.android.framework

import com.picpay.desafio.android.business.data.repository.UsersRemoteDataSource
import com.picpay.desafio.android.business.data.repository.UsersRepository
import com.picpay.desafio.android.business.domain.model.User
import com.picpay.desafio.android.framework.network.response.UserResponse
import com.picpay.desafio.android.framework.network.response.toModel
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersRemoteDataSource<List<UserResponse>>
) : UsersRepository {

    override suspend fun getUsers(): List<User> = remoteDataSource.getUsers().map {
        it.toModel()
    }
}