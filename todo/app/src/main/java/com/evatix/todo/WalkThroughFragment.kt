package com.evatix.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WalkThroughFragment : Fragment() {

    private lateinit var buttonGetStarted: Button
    private lateinit var buttonLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walk_through, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        buttonClickListener(view)

    }

    private fun initView(view: View) {


        buttonGetStarted = view.findViewById(R.id.button_get_started)
        buttonLogin = view.findViewById(R.id.button_log_in)

    }

    private fun buttonClickListener(view: View) {


        buttonGetStarted.setOnClickListener {


            view.findNavController()
                .navigate(R.id.action_WalkThroughFragment_to_RegistrationFragment)

        }

        buttonLogin.setOnClickListener {

            view.findNavController().navigate(R.id.action_WalkThroughFragment_to_loginFragment)

        }


    }
}