package com.evatix.todo.ui.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.evatix.todo.R
import com.evatix.todo.utils.Util
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegistrationFragment : Fragment() {


    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var buttonSignUpSubmit: Button

    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view);
        buttonClickListener(view)

        registrationViewModel.signUpResponse.observe(viewLifecycleOwner, Observer {
            if(it.response =="success"){


                view.findNavController().navigate(R.id.action_RegistrationFragment_to_loginFragment)
                Snackbar.make(constraintLayout, getString(R.string.sign_up_successful), Snackbar.LENGTH_SHORT).show()


            }else{

                Snackbar.make(constraintLayout, getString(R.string.went_wrong), Snackbar.LENGTH_SHORT).show()


            }


        } )


        registrationViewModel.signUpErrorResponse  .observe(viewLifecycleOwner, Observer {

            Snackbar.make(constraintLayout, getString(R.string.went_wrong), Snackbar.LENGTH_SHORT).show()


        })

    }


    private fun initView(view: View) {

        constraintLayout = view.findViewById(R.id.constraintLayoutSignUp)
        editTextUsername = view.findViewById(R.id.edit_text_username)
        editTextEmail = view.findViewById(R.id.edit_text_email)
        editTextPassword = view.findViewById(R.id.edit_text_password)
        editTextConfirmPassword = view.findViewById(R.id.edit_text_confirm_password)

        buttonSignUpSubmit = view.findViewById(R.id.button_sign_up)


    }

    private fun buttonClickListener(view: View) {

        buttonSignUpSubmit.setOnClickListener {



            username = editTextUsername.text.toString()
            email = editTextEmail.text.toString()
            password = editTextPassword.text.toString()
            confirmPassword = editTextConfirmPassword.text.toString()

            val result = checkInputFieldValidation(username, email, password, confirmPassword)

            if (result == getString(R.string.Okay)) {

                if(Util.isOnline(activity)) {

                    registrationViewModel.sendSignUpRequest(
                        username,
                        email,
                        password
                    )
                }else{

                    Snackbar.make(constraintLayout, getString(R.string.please_check_network), Snackbar.LENGTH_SHORT).show()


                }

            } else {

                Snackbar.make(constraintLayout, result, Snackbar.LENGTH_SHORT).show()

            }

        }
    }

    private fun checkInputFieldValidation(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): String {


        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {


            return getString(R.string.empty_field)

        } else if (!Util.isValidEmail(email)) {

            return getString(R.string.invalid_email)

        } else if (password != confirmPassword) {

            return getString(R.string.password_not_match)
        } else if (password.length < 6) {

            return getString(R.string.password_not_match)

        }


        return getString(R.string.Okay)

    }


}