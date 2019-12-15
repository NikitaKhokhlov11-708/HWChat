package com.example.hwchat.models

class Response<T>(val data: T?, val error: Throwable?) {

    companion object {
        fun <T> success(data: T): Response<T> = Response(data, null)

        fun error(error: Throwable): Response<String>? = Response(null, error)
    }
}
