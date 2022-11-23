package com.example.simpleregistration.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.model.UserPersonalInfo
import com.example.simpleregistration.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel> { ProfileViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.getUserData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        observeViewModel()

        binding.btnLogout.setOnClickListener {
            signOut()
        }

        return binding.root
    }

    private fun signOut() {
        viewModel.singOut()
        findNavController().navigate(R.id.action_profileFragment_to_main_graph)
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
}