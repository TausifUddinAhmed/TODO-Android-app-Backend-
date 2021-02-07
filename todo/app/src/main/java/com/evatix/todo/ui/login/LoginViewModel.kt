package com.evatix.todo.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.evatix.todo.model.LoginResponse
import com.evatix.todo.model.SignUpResponse
import com.evatix.todo.repository.RemoteRepository
import com.evatix.todo.utils.Util

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    private var remoteRepository: RemoteRepository

    var loginResponse = MutableLiveData<LoginResponse>()
    var loginErrorResponse = MutableLiveData<String>()

    init {

        remoteRepository = RemoteRepository()
    }

    fun sendLoginRequest(email: String, password: String) {




        val passwordHash = Util.sha256(password)

        Log.e("LogInViewModel", "PasswordHash : "+passwordHash)

        remoteRepository.sendSignUpRequest(email,passwordHash,
            object : RemoteRepository.OnLoginResponseData {

                override fun onSuccess(data: LoginResponse) {



                    loginResponse.postValue(data)

                }

                override fun onError(message: String) {


                    loginErrorResponse.postValue(message)
                    Log.e("LoginData", message.toString())


                }


            })
    }

}