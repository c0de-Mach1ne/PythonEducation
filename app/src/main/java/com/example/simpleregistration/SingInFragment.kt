package com.example.simpleregistration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class SingInFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.lessonFragment)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.registerFragmentEmailPass)
        }

    }
}

//        binding.btnLogin.setOnClickListener {

//            val email = binding.etEmail.text.toString()
//            val pass = binding.etPassword.text.toString()

//            if (email.isNotBlank() && pass.isNotBlank()) {
//                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                    } else {
//                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }