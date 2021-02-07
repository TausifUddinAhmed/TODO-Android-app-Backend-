package com.evatix.todo.model
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("response")
    val response: String // success
)