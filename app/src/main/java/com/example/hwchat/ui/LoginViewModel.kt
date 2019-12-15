package com.example.hwchat.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hwchat.api.LoginApiService
import com.example.hwchat.data.API_BASE
import com.example.hwchat.data.DEVICE_ID
import com.example.hwchat.data.PREFERENCES
import com.example.hwchat.data.USERNAME
import com.example.hwchat.models.Response
import com.example.hwchat.models.User
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel(private val context: Context) : ViewModel() {
    val loginLiveData = MutableLiveData<Response<String>>()
    private val disposables = CompositeDisposable()
    private val api = Retrofit.Builder()
        .baseUrl(API_BASE)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .create(LoginApiService::class.java)

    fun login(user: User) {
        disposables.add(
            api.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.status == "ok") {
                        val sharedPref =
                            context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                        sharedPref.edit()
                            .putString(DEVICE_ID, user.deviceId)
                            .putString(USERNAME, user.username).apply()
                        loginLiveData.value = Response.success(response.status)
                    }
                }, {
                    loginLiveData.value = Response.error(it)
                    it.printStackTrace()
                })
        )
    }
}
