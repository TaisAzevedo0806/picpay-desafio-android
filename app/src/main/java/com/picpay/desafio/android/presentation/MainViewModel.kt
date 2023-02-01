package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.business.domain.model.User
import com.picpay.desafio.android.business.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _snackBar = MutableLiveData(false)
    val snackBar: LiveData<Boolean>
        get() = _snackBar

    private val _progressBar = MutableLiveData(false)
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _users = MutableLiveData<List<User>?>()
    val users: LiveData<List<User>?>
        get() = _users

    fun getUsers() {
        viewModelScope.launch {
            try {
                _progressBar.value = true
                _users.value = getUsersUseCase()
            } catch (error: Throwable) {
                _snackBar.value = true
            } finally {
                _progressBar.value = false
            }
        }
    }

    fun onSnackBarShown() {
        _snackBar.value = false
    }
}