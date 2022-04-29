package com.example.physicswallah.api

import com.example.physicswallah.model.UsersResponse

import retrofit2.Call
import retrofit2.http.*

interface TeachersApi {
//    To get teachers details
    @GET("users")
    fun getTeachersDetails(
    ):Call<UsersResponse>
}

