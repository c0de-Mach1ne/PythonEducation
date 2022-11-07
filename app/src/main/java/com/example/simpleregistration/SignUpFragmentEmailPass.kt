package com.example.simpleregistration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.databinding.FragmentRegisterEmailPassBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragmentEmailPass : Fragment(R.layout.fragment_register_email_pass) {

    private lateinit var binding: FragmentRegisterEmailPassBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterEmailPassBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnNextStep.setOnClickListener {
            findNavController().navigate(R.id.registerFragmentFullName)
        }

//        binding.btnRegistration.setOnClickListener {
//
//            val email = binding.etEmail.text.toString()
//            val pass = binding.etPassword.text.toString()
//            val confirmPass = binding.etConfirmPassword.text.toString()
//
//            if (email.isNotBlank() && pass.isNotBlank()) {
//                if (pass == confirmPass) {
//                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
//                            findNavController().popBackStack()
//                        } else {
//                            Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
//                }
//            }
//        }
    }
}