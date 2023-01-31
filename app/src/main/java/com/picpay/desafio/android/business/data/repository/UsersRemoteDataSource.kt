package com.picpay.desafio.android.business.data.repository

interface UsersRemoteDataSource<T> {

    suspend fun getUsers(): T
}