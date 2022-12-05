package com.example.simpleregistration.fragments.quiz.create

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class CreateQuizFragment: Fragment() {

    private val viewModel by viewModels<CreateQuizViewModel> { CreateQuizViewModelFactory() }
}