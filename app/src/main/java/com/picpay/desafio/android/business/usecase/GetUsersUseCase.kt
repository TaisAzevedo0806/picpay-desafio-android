package com.picpay.desafio.android.business.usecase

import com.picpay.desafio.android.business.domain.model.User

interface GetUsersUseCase {

    operator fun invoke(): List<User>
}

class GetUsersUseCaseImpl : GetUsersUseCase {

    override fun invoke(): List<User> {
        TODO("Not yet implemented")
    }
}