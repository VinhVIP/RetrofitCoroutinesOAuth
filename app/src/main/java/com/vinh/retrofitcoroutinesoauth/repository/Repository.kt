package com.vinh.retrofitcoroutinesoauth.repository

import android.content.Context
import com.vinh.retrofitcoroutinesoauth.api.RetrofitInstance
import com.vinh.retrofitcoroutinesoauth.data.requests.LoginRequest
import com.vinh.retrofitcoroutinesoauth.data.responses.LoginResponseData
import com.vinh.retrofitcoroutinesoauth.data.responses.UserResponseData
import retrofit2.Response

class Repository(private val context: Context) {

    suspend fun login(request: LoginRequest): Response<LoginResponseData> {
//        return RetrofitInstance.getAuthApi(context).login(request)
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProfile(): Response<UserResponseData> {
        return RetrofitInstance.getAuthApi(context).getProfile()
    }

}