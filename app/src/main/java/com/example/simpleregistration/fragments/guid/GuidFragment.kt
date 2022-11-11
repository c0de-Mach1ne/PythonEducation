package com.example.simpleregistration.fragments.guid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.viewmode.AuthViewModel
import com.example.simpleregistration.auth.viewmode.AuthViewModelFactory

class GuidFragment: Fragment(R.layout.fragment_giud) {
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkUser()
    }
}