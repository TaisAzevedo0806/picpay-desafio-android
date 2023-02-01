package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.business.domain.model.User
import com.picpay.desafio.android.business.usecase.GetUsersUseCase
import com.picpay.desafio.android.framework.network.ResultStatus
import com.picpay.desafio.android.framework.network.ResultStatus.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _usersResultStatus = MutableLiveData<ResultStatus>(NotLoading)
    val usersResultStatus: LiveData<ResultStatus>
        get() = _usersResultStatus

    private val _users = MutableLiveData<List<User>?>()
    val users: LiveData<List<User>?>
        get() = _users

    fun getUsers() {
        viewModelScope.launch {
            try {
                _usersResultStatus.value = Loading
                _users.value = getUsersUseCase()
            } catch (error: Throwable) {
                _usersResultStatus.value = Error(error)
            } finally {
                _usersResultStatus.value = NotLoading
            }
        }
    }
}