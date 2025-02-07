package com.picpay.desafio.android.framework.remote

import com.picpay.desafio.android.business.data.repository.UsersRemoteDataSource
import com.picpay.desafio.android.framework.network.PicPayService
import com.picpay.desafio.android.framework.network.response.UserResponse
import javax.inject.Inject

class RetrofitUsersDataSource @Inject constructor(
    private val service: PicPayService
) : UsersRemoteDataSource<MutableList<UserResponse>> {

    override suspend fun getUsers(): MutableList<UserResponse> = service.getUsers()
}