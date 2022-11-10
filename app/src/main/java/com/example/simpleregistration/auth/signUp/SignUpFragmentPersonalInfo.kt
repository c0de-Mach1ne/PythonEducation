package com.example.simpleregistration.auth.signUp

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.viewmode.AuthViewModel
import com.example.simpleregistration.auth.viewmode.AuthViewModelFactory
import com.example.simpleregistration.databinding.FragmentSignUpPersonalInfoBinding

class SignUpFragmentPersonalInfo : Fragment(R.layout.fragment_sign_up_personal_info) {

    private lateinit var binding: FragmentSignUpPersonalInfoBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpPersonalInfoBinding.bind(view)

        binding.cbIsTeacher.setOnCheckedChangeListener { _, isChecked ->
            binding.inputLayoutSecretCode.isVisible = isChecked
        }

        binding.btnFinalRegistration.setOnClickListener {
            signUpWithPersonalInfo()
        }
    }

    private fun signUpWithPersonalInfo() {
        val name = binding.etName.text.toString()
        val sureName = binding.etSureName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()
        val isTeacher = binding.cbIsTeacher.isChecked

    }

    private fun navigateToSignIn() =
        findNavController().popBackStack(R.id.signInFragment, false)
}