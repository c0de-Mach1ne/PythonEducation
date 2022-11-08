package com.example.simpleregistration.auth.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentSignUpFullnameBinding

class SignUpFragmentFullName: Fragment(R.layout.fragment_sign_up_fullname) {

    private lateinit var binding: FragmentSignUpFullnameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpFullnameBinding.bind(view)

        binding.btnFinalRegistration.setOnClickListener {
            findNavController().popBackStack(R.id.signInFragment, false)
        }
    }
}