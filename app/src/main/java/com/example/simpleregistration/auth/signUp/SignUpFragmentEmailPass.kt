package com.example.simpleregistration.auth.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.auth.viewmode.AuthViewModel
import com.example.simpleregistration.auth.viewmode.AuthViewModelFactory
import com.example.simpleregistration.databinding.FragmentSignUpEmailPassBinding

class SignUpFragmentEmailPass : Fragment(R.layout.fragment_sign_up_email_pass) {

    private lateinit var binding: FragmentSignUpEmailPassBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observableViewModel()
        binding = FragmentSignUpEmailPassBinding.bind(view)

        binding.btnNextStep.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()

        if (email.isNotBlank() && pass.isNotBlank() && confirmPass.isNotBlank()) {
            if (pass == confirmPass) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                viewModel.signUpWithEmailAndPassword(email, pass)
            }
        } else {
            Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observableViewModel(){
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> navigateToSignUpPersonalInfo()
                is AuthState.Error -> Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToSignUpPersonalInfo() = findNavController().navigate(R.id.signUpFragmentFullName)
}