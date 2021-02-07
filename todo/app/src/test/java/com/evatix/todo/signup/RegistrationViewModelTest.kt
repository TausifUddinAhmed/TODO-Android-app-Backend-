package com.evatix.todo.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evatix.todo.ui.signup.RegistrationViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun sendLoginRequestWithUniqueEmail() {


        val registrationViewModel =
            RegistrationViewModel(ApplicationProvider.getApplicationContext())


        registrationViewModel.sendSignUpRequest("aaa", "ne$$$$$2@gmail.com", "12345")



        val value_success = registrationViewModel.signUpResponse.getOrAwaitValue()

        Assert.assertEquals(
            value_success.response,
            "success"
        )


    }

    @Test
    fun sendLoginRequestWithAlreadyRegisteredEmail() {


        val registrationViewModel =
            RegistrationViewModel(ApplicationProvider.getApplicationContext())


        registrationViewModel.sendSignUpRequest("aaa", "qapp@gmail.com", "12345")


        val value_error = registrationViewModel.signUpResponse.getOrAwaitValue()

        Assert.assertEquals(
            value_error.response,
            "Email Already exists"
        )


    }
}