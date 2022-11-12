package com.example.simpleregistration.fragments.guid

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.viewmode.AuthViewModel
import com.example.simpleregistration.auth.viewmode.AuthViewModelFactory

class StudentFragment: Fragment(R.layout.fragment_student) {
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}