package com.picpay.desafio.android.framework.network

sealed class ResultStatus {

    object Loading : ResultStatus()
    object NotLoading : ResultStatus()
    data class Error(val throwable: Throwable) : ResultStatus()

    override fun toString(): String {
        return when (this) {
            Loading -> "Loading"
            NotLoading -> "NotLoading"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}