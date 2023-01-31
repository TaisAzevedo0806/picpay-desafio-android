package com.picpay.desafio.android.business.data.repository

import com.picpay.desafio.android.business.domain.model.User

interface UsersRepository {

    suspend fun getUsers(): List<User>
}