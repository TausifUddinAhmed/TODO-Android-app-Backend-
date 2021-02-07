package com.evatix.todo.ui.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.evatix.todo.model.SignUpResponse
import com.evatix.todo.repository.RemoteRepository
import com.evatix.todo.utils.Util

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {


    private var remoteRepository: RemoteRepository

    var signUpResponse = MutableLiveData<SignUpResponse>()
    var signUpErrorResponse = MutableLiveData<String>()

    init {

        remoteRepository = RemoteRepository()
    }

    fun sendSignUpRequest( username : String,email: String, password: String) {




        val passwordHash = Util.sha256(password)

        Log.e("RegistrationViewModel", "PasswordHash : "+passwordHash)

        remoteRepository.sendSignUpRequest(username,email,passwordHash,
            object : RemoteRepository.OnSingUpResponseData {

                override fun onSuccess(data: SignUpResponse) {



                    signUpResponse.postValue(data)

                }

                override fun onError(message: String) {


                    signUpErrorResponse.postValue(message)
                    Log.e("LoginData", message.toString())


                }


            })
    }

}