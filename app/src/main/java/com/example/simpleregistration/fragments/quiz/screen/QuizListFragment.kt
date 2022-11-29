package com.example.simpleregistration.fragments.quiz.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentContentBinding
import com.example.simpleregistration.fragments.quiz.QuizAdapter
import com.example.simpleregistration.fragments.quiz.viewmodel.QuizListViewModel
import com.example.simpleregistration.fragments.quiz.viewmodel.QuizListViewModelFactory
import com.example.simpleregistration.utils.state_model.Loading

class QuizListFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<QuizListViewModel> { QuizListViewModelFactory() }
    private val adapter by lazy { QuizAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getQuizList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecycler()
    }

    private fun observeViewModel(){
        viewModel.quizList.observe(viewLifecycleOwner){ quizList ->
            adapter.submitList(quizList)
            quizList.forEach { quiz ->
                adapter.notifyItemChanged(quizList.indexOf(quiz))
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner){
            when (it) {
                is Loading.Start -> {
                    binding.progressBar.isVisible = true
                }
                is Loading.Stop -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun initRecycler() = with(binding){
        recyclerView.adapter = adapter
    }
}