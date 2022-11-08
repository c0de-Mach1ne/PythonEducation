package com.example.simpleregistration.auth.signIn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.AuthViewModel
import com.example.simpleregistration.auth.AuthViewModelFactory
import com.example.simpleregistration.databinding.FragmentSignInBinding
import com.example.simpleregistration.repository.AuthRepositoryImpl

class SingInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<AuthViewModel>{
        AuthViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        observableViewModel()
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()

            if (email.isNotBlank() && pass.isNotBlank()) {
                viewModel.signIn(email, pass)
            } else {
                Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragmentEmailPass)
        }
    }



    private fun observableViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMes.observe(viewLifecycleOwner) {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        }

        viewModel.isSuccess.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
            findNavController().navigate(R.id.guidFragment)
        }
    }
}