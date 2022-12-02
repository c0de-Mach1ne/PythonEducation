package com.example.simpleregistration.auth.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.utils.state_model.AuthState
import com.example.simpleregistration.auth.viewmodels.AuthViewModel
import com.example.simpleregistration.auth.viewmodels.AuthViewModelFactory
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentSignUpPersonalInfoBinding

class SignUpFragmentPersonalInfo : Fragment(R.layout.fragment_sign_up_personal_info) {

    companion object {
        const val SECRET_CODE = "1337"
    }

    private lateinit var binding: FragmentSignUpPersonalInfoBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding = FragmentSignUpPersonalInfoBinding.bind(view)

        binding.cbIsTeacher.setOnCheckedChangeListener { _, isChecked ->
            binding.inputLayoutSecretCode.isVisible = isChecked
            binding.etSecretCode.text?.clear()
        }

        binding.btnFinalRegistration.setOnClickListener {
            signUpWithPersonalInfo()
        }
    }

    private fun signUpWithPersonalInfo() {
        val name = binding.etName.text.toString()
        val sureName = binding.etSureName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()
        val secretCode = binding.etSecretCode.text.toString()
        var isTeacher = false

        if (name.isNotBlank() && sureName.isNotBlank() && patronymic.isNotBlank()) {
            if (binding.cbIsTeacher.isChecked) {
                if (secretCode == SECRET_CODE) {
                    isTeacher = binding.cbIsTeacher.isChecked
                } else {
                    Toast.makeText(context, "Incorrect secret code", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            viewModel.signUpWithPersonalInfo(name, sureName, patronymic, isTeacher)
        } else Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.Success -> navigateToSignIn()
                is AuthState.Error -> Toast.makeText(context, "${it.mes}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun navigateToSignIn() =
        findNavController().popBackStack(R.id.signInFragment, false)
}