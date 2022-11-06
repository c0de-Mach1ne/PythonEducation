package com.example.simpleregistration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simpleregistration.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()


//        binding.btnLogin.setOnClickListener {
//
//            val email = binding.etEmail.text.toString()
//            val pass = binding.etPassword.text.toString()
//
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
//        }

//        binding.btnMoveToReg.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//        }
    }
}