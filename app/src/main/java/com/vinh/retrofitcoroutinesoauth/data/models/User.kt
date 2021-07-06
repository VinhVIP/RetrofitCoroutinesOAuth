package com.vinh.retrofitcoroutinesoauth.data.models

data class User(
    val _id: String,
    val firstName: String,
    val gender: String,
    val gravatar: String,
    val lastName: String,
    val school: String,
    val studentID: String
)