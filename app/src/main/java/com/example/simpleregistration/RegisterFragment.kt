package com.example.simpleregistration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simpleregistration.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()

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