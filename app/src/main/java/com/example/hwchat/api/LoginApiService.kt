package com.example.hwchat.api

import com.example.hwchat.models.LoginResponse
import com.example.hwchat.models.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService{
    @POST("login")
    fun login(@Body user: User): Single<LoginResponse>
}