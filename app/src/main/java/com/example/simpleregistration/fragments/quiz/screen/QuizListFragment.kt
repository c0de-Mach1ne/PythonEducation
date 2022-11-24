package com.example.simpleregistration.fragments.quiz.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentContentBinding
import com.example.simpleregistration.fragments.quiz.QuizAdapter
import com.example.simpleregistration.fragments.quiz.viewmodel.QuizListViewModel
import com.example.simpleregistration.fragments.quiz.viewmodel.QuizListViewModelFactory

class QuizListFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<QuizListViewModel> { QuizListViewModelFactory() }
    private val adapter by lazy { QuizAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater, container, false)
        viewModel.getQuizList()
        observeViewModel()
        initRecycler()
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.quizList.observe(viewLifecycleOwner){ quizList ->
            adapter.submitList(quizList)
        }
    }

    private fun initRecycler() = with(binding){
        recyclerView.adapter = adapter
    }
}