package com.example.simpleregistration.fragments.teacher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentTeacherBinding
import com.example.simpleregistration.fragments.profile.ProfieViewModel
import com.example.simpleregistration.fragments.profile.ProfileViewModelFactory

class TeacherFragment : Fragment(R.layout.fragment_teacher) {

    private val viewModel by viewModels<ProfieViewModel> { ProfileViewModelFactory() }
    private lateinit var binding: FragmentTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeacherBinding.bind(view)
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