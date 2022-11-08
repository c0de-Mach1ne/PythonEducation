package com.example.simpleregistration.auth.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentSignUpEmailPassBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragmentEmailPass : Fragment(R.layout.fragment_sign_up_email_pass) {

    private lateinit var binding: FragmentSignUpEmailPassBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpEmailPassBinding.bind(view)

        binding.btnNextStep.setOnClickListener {
            findNavController().navigate(R.id.signUpFragmentFullName)
        }
    }
}