package com.vinh.retrofitcoroutinesoauth.data.responses

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)