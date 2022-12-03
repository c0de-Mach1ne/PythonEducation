package com.example.simpleregistration.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.simpleregistration.R

// TODO: разобраться с этим кодом. Код взят у Романа Андрюсченко с урока по навигации
fun Fragment.findTopNavController(): NavController {
    val topLevelHost =
        requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}