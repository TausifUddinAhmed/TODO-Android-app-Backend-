package com.evatix.todo.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.evatix.todo.R


class RestPasswordSuccesFragment : Fragment() {


    private  lateinit var buttonLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_password_succes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.button_log_in)

        buttonLogin.setOnClickListener {

            view.findNavController().navigate(R.id.action_restPasswordSuccesFragment_to_loginFragment)

        }



    }


}