package com.example.hwchat.models

data class Message(
    val id: Long,
    val message: String = "",
    val user: String = ""
)