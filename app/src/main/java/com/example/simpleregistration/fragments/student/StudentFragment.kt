package com.example.simpleregistration.fragments.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentStudentBinding
import com.example.simpleregistration.fragments.UserViewModel
import com.example.simpleregistration.fragments.UserViewModelFactory

class StudentFragment : Fragment(R.layout.fragment_student) {

    private val viewModel by viewModels<UserViewModel> { UserViewModelFactory() }
    private lateinit var binding: FragmentStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentBinding.bind(view)
        observeViewModel()
        binding.btnLogout.setOnClickListener {
            signOut()
        }
    }

    private fun observeViewModel() {
        viewModel.userData.observe(viewLifecycleOwner) { userPersonalInfo ->
            updateUi(userPersonalInfo)
        }
    }

    private fun updateUi(userPersonalInfo: UserPersonalInfo) {
        binding.tvName.text = userPersonalInfo.name
        binding.tvPatronymic.text = userPersonalInfo.patronymic
        binding.tvSureName.text = userPersonalInfo.sureName
        binding.tvRole.text = userPersonalInfo.teacherFlag.toString()
    }

    private fun signOut() {
        viewModel.singOut()
        findNavController().popBackStack()
        findNavController().navigate(R.id.signInFragment)
    }
}