package com.vinh.retrofitcoroutinesoauth.api

import com.vinh.retrofitcoroutinesoauth.data.requests.LoginRequest
import com.vinh.retrofitcoroutinesoauth.data.responses.LoginResponseData
import com.vinh.retrofitcoroutinesoauth.data.responses.UserResponseData
import com.vinh.retrofitcoroutinesoauth.utils.Constants.Companion.LOGIN_URL
import com.vinh.retrofitcoroutinesoauth.utils.Constants.Companion.PROFILE_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @POST(LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponseData>

    @GET(PROFILE_URL)
    suspend fun getProfile(): Response<UserResponseData>
}