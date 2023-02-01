package com.picpay.desafio.android.business.usecase

import com.picpay.desafio.android.business.data.repository.UsersRepository
import com.picpay.desafio.android.business.domain.model.User
import javax.inject.Inject

interface GetUsersUseCase {

    suspend operator fun invoke(): List<User>
}

class GetUsersUseCaseImpl @Inject constructor(
    private val repository: UsersRepository
) : GetUsersUseCase {

    override suspend fun invoke(): List<User> = repository.getUsers()
}