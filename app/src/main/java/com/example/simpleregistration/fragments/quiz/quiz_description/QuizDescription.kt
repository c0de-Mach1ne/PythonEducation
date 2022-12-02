package com.example.simpleregistration.fragments.quiz.quiz_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentQuizDescriptionBinding

class QuizDescription : Fragment(R.layout.fragment_quiz_description) {

    private lateinit var binding: FragmentQuizDescriptionBinding
    private val viewModel by viewModels<QuizDescriptionViewModel> { QuizDescriptionViewModelFactory() }
    private val args by navArgs<QuizDescriptionArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuizDescriptionBinding.inflate(layoutInflater, container, false)
        binding.testTextView.text = args.quiz.title
        return binding.root
    }
}