package com.evatix.todo.model
import com.google.gson.annotations.SerializedName


data class SignUpResponse(
    @SerializedName("response")
    val response: String // success
)