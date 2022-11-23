package com.example.simpleregistration.fragments.guid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentContentBinding
import com.example.simpleregistration.fragments.ContentAdapter

class GuidFragment: Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<GuidViewModel> { GuidViewModelFactory() }
    private val guidAdapter by lazy { ContentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.guidList.observe(viewLifecycleOwner){
            Log.d("TAG", "list size ${it.size}")
            guidAdapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
        observeViewModel()
        initRecycler()
    }

    private fun initRecycler() = with(binding) {
        recyclerView.adapter = guidAdapter
    }
}