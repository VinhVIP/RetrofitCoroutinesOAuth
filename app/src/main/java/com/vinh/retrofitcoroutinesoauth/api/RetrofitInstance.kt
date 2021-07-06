package com.vinh.retrofitcoroutinesoauth.api

import android.content.Context
import com.vinh.retrofitcoroutinesoauth.utils.AuthInterceptor
import com.vinh.retrofitcoroutinesoauth.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: MyApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    fun getAuthApi(context: Context): MyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(context))
            .build()
            .create(MyApi::class.java)
    }

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}