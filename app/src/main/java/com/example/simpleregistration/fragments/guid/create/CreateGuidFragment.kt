package com.example.simpleregistration.fragments.guid.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentCreateGuidBinding
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.utils.state_model.LoadingState
import kotlin.random.Random

class CreateGuidFragment : Fragment(R.layout.fragment_create_guid) {

    private lateinit var binding: FragmentCreateGuidBinding
    private val viewModel by viewModels<CreateGuidViewModel> { CreateGuidViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateGuidBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        binding.btnCreateGuid.setOnClickListener {
            val title = binding.etGuidTitle.text.toString()
            val guidLink = binding.etGuidLink.text.toString()
            val uId = Random.nextInt(0, 100)

            viewModel.pushGuid(Guid(
                id = uId,
                title = title,
                urlReference = guidLink,
                description = ""
            ))
        }
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingState.Start -> {
                    binding.progressBar.isVisible = true
                }
                is LoadingState.Stop -> {
                    binding.progressBar.isVisible = false
                }
                is LoadingState.Error -> {
                    Toast.makeText(binding.root.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}