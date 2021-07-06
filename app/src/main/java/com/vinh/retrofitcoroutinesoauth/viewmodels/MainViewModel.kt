package com.vinh.retrofitcoroutinesoauth.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinh.retrofitcoroutinesoauth.data.requests.LoginRequest
import com.vinh.retrofitcoroutinesoauth.data.responses.LoginResponseData
import com.vinh.retrofitcoroutinesoauth.data.responses.UserResponseData
import com.vinh.retrofitcoroutinesoauth.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val loginResponse:MutableLiveData<Response<LoginResponseData>> = MutableLiveData()

    val profile: MutableLiveData<Response<UserResponseData>> = MutableLiveData()

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            println("---------start login---------")
            try {
                val response = repository.login(request)
                loginResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                loginResponse.value = null
            }

            println("---------end login---------")
        }
    }


    fun getProfile() {
        viewModelScope.launch {
            println("------start fetch profile------------")
            profile.value = repository.getProfile()
            println("------end fetch profile------------")
        }
    }

}