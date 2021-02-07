package com.evatix.todo.repository

import android.util.Log
import com.evatix.todo.model.LoginResponse
import com.evatix.todo.model.SignUpResponse
import com.evatix.todo.networking.ApiClient
import com.evatix.todo.networking.ApiInterfaceService
import com.evatix.todo.utils.Constant.HTTP_STATUS_NOT_FOUND
import com.evatix.todo.utils.Constant.HTTP_STATUS_UNAUTHORIZED
import com.evatix.todo.utils.Constant.INTERNAL_SERVER_ERROR
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

open class RemoteRepository {




    private var apiClient: ApiClient
    private var apiInterfaceService: ApiInterfaceService


    init {

        apiClient = ApiClient()
        apiInterfaceService = apiClient.provideApi()

    }

    interface OnSingUpResponseData {
        fun onSuccess(data: SignUpResponse)
        fun onError(message: String)
    }

    fun sendSignUpRequest(
        username : String,
        email: String, password: String, onSignUpResponseData: OnSingUpResponseData
    ) {
        apiInterfaceService.createAccount(username, email, password)
            .enqueue(object : retrofit2.Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {

                    if (response.isSuccessful) {
                        (response.body())?.let {

                            onSignUpResponseData.onSuccess(it)

                        }
                    } else if (response.code() ==HTTP_STATUS_NOT_FOUND ) {



                        onSignUpResponseData.onError("failed")


                    } else if (response.code() == HTTP_STATUS_UNAUTHORIZED) {

                        onSignUpResponseData.onError("failed")


                    } else if (response.code() == INTERNAL_SERVER_ERROR) {



                        onSignUpResponseData.onError("Server Error")


                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.e("Failed", t.message.toString())
                    onSignUpResponseData.onError(t.message.toString())


                }
            })
    }

    interface OnLoginResponseData {
        fun onSuccess(data: LoginResponse)
        fun onError(message: String)
    }

    fun sendSignUpRequest(
        email: String, password: String, onLoginResponseData:OnLoginResponseData
    ) {
        apiInterfaceService.doLogin( email, password)
            .enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    if (response.isSuccessful) {
                        (response.body())?.let {

                            onLoginResponseData.onSuccess(it)

                        }
                    } else if (response.code() ==HTTP_STATUS_NOT_FOUND ) {



                        onLoginResponseData.onError("failed")


                    } else if (response.code() == HTTP_STATUS_UNAUTHORIZED) {

                        onLoginResponseData.onError("failed")


                    } else if (response.code() == INTERNAL_SERVER_ERROR) {



                        onLoginResponseData.onError("Server Error")


                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Failed", t.message.toString())
                    onLoginResponseData.onError(t.message.toString())


                }
            })
    }
}