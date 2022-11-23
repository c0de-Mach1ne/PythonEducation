package com.example.simpleregistration.fragments.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.FragmentContentBinding

class QuizFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding
    private val viewModel by viewModels<QuizViewModel> { QuizViewModelFactory() }
    private val adapter by lazy {  }


}