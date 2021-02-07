package com.evatix.todo.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.evatix.todo.R
import com.evatix.todo.ui.signup.RegistrationViewModel
import com.evatix.todo.utils.Util
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLoginSubmit: Button

    private  lateinit var textViewForgotPassword: TextView

    private lateinit var email: String
    private lateinit var password: String

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        buttonClickListener(view)

        loginViewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            if(it.response == "success"){


                view.findNavController().navigate(R.id.action_loginFragment_to_todoFragment)

                Snackbar.make(constraintLayout, "Login Successful", Snackbar.LENGTH_SHORT).show()


            }else{

                Snackbar.make(constraintLayout, getString(R.string.went_wrong), Snackbar.LENGTH_SHORT).show()


            }


        })


        loginViewModel.loginErrorResponse.observe(viewLifecycleOwner, Observer {

            Snackbar.make(constraintLayout, getString(R.string.went_wrong), Snackbar.LENGTH_SHORT).show()


        })



    }

    private fun initView(view: View) {

        constraintLayout = view.findViewById(R.id.constraintLayoutLogIn)
        editTextEmail = view.findViewById(R.id.edit_text_email)
        editTextPassword = view.findViewById(R.id.edit_text_password)
        buttonLoginSubmit = view.findViewById(R.id.button_log_in)
        textViewForgotPassword = view.findViewById(R.id.text_view_forgot_password)


    }

    private fun buttonClickListener(view: View) {

        buttonLoginSubmit.setOnClickListener {

            email = editTextEmail.text.toString()
            password = editTextPassword.text.toString()

            val result = checkInputFieldValidation(email, password)

            if (result == getString(R.string.Okay)) {

                if (Util.isOnline(activity)) {

                    loginViewModel.sendLoginRequest(
                        email,
                        password
                    )
                } else {

                    Snackbar.make(
                        constraintLayout,
                        getString(R.string.please_check_network),
                        Snackbar.LENGTH_SHORT
                    ).show()


                }

            } else {

                Snackbar.make(constraintLayout, result, Snackbar.LENGTH_SHORT).show()


            }

        }

        textViewForgotPassword.setOnClickListener {


            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)


        }


    }

    private fun checkInputFieldValidation(
        email: String,
        password: String
    ): String {


        if (email.isEmpty() || password.isEmpty()) {


            return getString(R.string.empty_field)

        } else if (!Util.isValidEmail(email)) {

            return getString(R.string.invalid_email)

        }


        return getString(R.string.Okay)
    }


}