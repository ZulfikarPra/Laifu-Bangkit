package com.dicoding.lifeu.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("token")
    val token: String
)