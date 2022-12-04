package com.example.simpleregistration.fragments.quiz.quiz_list

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
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.fragments.quiz.QuizAdapter
import com.example.simpleregistration.utils.ItemClickListener
import com.example.simpleregistration.utils.state_model.Loading

class QuizListFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<QuizListViewModel> { QuizListViewModelFactory() }

    private val adapter by lazy {
        QuizAdapter( object : ItemClickListener<Quiz> {
                override fun onClickItem(value: Quiz) {
                    findNavController().navigate(QuizListFragmentDirections
                        .actionQuizFragmentToQuizDescription(value))
                }
            }
        )
    }

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
        observeViewModel()
        initRecycler()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.quizList.observe(viewLifecycleOwner) { quizList ->
            adapter.submitList(quizList)
            quizList.forEach { quiz ->
                adapter.notifyItemChanged(quizList.indexOf(quiz))
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

    private fun initRecycler() = with(binding) {
        recyclerView.adapter = adapter
    }
}