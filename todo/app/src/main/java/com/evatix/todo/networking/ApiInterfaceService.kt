package com.evatix.todo.networking

import com.evatix.todo.model.LoginResponse
import com.evatix.todo.model.SignUpResponse
import com.mymaskreminder.networking.ApiEndPoint.LOGIN_API
import com.mymaskreminder.networking.ApiEndPoint.REGISTER_API
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterfaceService {

    @FormUrlEncoded
    @Headers(
        "Accept: application/json"
    )
    @POST(LOGIN_API)
    fun doLogin(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<LoginResponse>

    @FormUrlEncoded
    @Headers(
        "Accept: application/json"
    )
    @POST(REGISTER_API)
    fun createAccount(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<SignUpResponse>





}