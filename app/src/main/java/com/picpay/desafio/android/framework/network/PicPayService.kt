package com.picpay.desafio.android.framework.network

import com.picpay.desafio.android.business.domain.model.User
import com.picpay.desafio.android.framework.network.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): MutableList<UserResponse>
}