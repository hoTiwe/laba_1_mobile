package com.example.laba2.api

object Common {
    val retrofitService: JSONApi
        get() = networkservice.getClient().create(JSONApi::class.java)
}