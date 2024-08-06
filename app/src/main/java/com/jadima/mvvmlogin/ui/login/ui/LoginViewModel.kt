package com.jadima.mvvmlogin.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _btnLoginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: LiveData<Boolean> = _btnLoginEnabled

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _btnLoginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }

    suspend fun onLoginClicked() {
        _loading.value = true

        delay(3000)

        _loading.value = false
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    private fun isValidEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}