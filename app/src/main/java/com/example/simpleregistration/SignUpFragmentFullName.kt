package com.example.simpleregistration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.databinding.FragmentRegisterEmailPassBinding
import com.example.simpleregistration.databinding.FragmentRegisterFullNameBinding

class SignUpFragmentFullName: Fragment(R.layout.fragment_register_full_name) {

    private lateinit var binding: FragmentRegisterFullNameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterFullNameBinding.bind(view)

        binding.btnFinalRegistration.setOnClickListener {
            findNavController().popBackStack(R.id.signInFragment, false)
        }
    }
}