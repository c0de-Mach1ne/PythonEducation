package com.example.simpleregistration.fragments.guid.guid_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentContentBinding
import com.example.simpleregistration.fragments.guid.GuidListAdapter
import com.example.simpleregistration.fragments.model.Guid
import com.example.simpleregistration.utils.ItemClickListener
import com.example.simpleregistration.utils.state_model.Loading

class GuidListFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<GuidListViewModel> { GuidListViewModelFactory() }

    private val adapter by lazy {
        GuidListAdapter(object : ItemClickListener<Guid> {
            override fun onClickItem(value: Guid) {
                findNavController().navigate(GuidListFragmentDirections.actionGuidFragmentToGuidFragment2(
                    value))
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGuidList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater, container, false)
        observeViewModel()
        initRecycler()
        binding.fabAddContent.setOnClickListener {
            findNavController().navigate(R.id.action_guidFragment_to_createGuidFragment)
        }
        return binding.root
    }

    private fun initRecycler() = with(binding) {
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.guidList.observe(viewLifecycleOwner) { guidList ->
            adapter.submitList(guidList)
            guidList.forEach { guid ->
                adapter.notifyItemChanged(guidList.indexOf(guid))
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is Loading.Start -> {
                    binding.progressBar.isVisible = true
                }
                is Loading.Stop -> {
                    binding.progressBar.isVisible = false
                }
                is Loading.Error -> {
                    Toast.makeText(binding.root.context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}