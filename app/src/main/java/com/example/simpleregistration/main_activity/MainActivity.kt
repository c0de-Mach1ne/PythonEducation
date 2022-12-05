package com.example.simpleregistration.main_activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.databinding.ActivityMainBinding
import com.example.simpleregistration.fragments.TabsFragment
import com.example.simpleregistration.fragments.model.Quiz
import com.example.simpleregistration.fragments.model.QuizAnswers
import com.example.simpleregistration.fragments.model.QuizQuestions
import com.example.simpleregistration.fragments.repository.DataRepository
import com.example.simpleregistration.utils.state_model.AuthState

// Todo #код : навигация написана по гайду Романа Андрюсченко: https://www.youtube.com/watch?v=mqorLkWtinU&t=1444s&ab_channel=RomanAndrushchenko
//  я обязан разобраться в коде и понять за что отвечает каждая строчка и почему такое решение было принято,
//  как данное решение можно оптимизировать
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory() }
    private val topLevelDestination = setOf(getTabsDestination(), getSignInDestination())

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.title = destination.label
            supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
        }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?,
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is TabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        createCorrectQuiz()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.toolbar)
        navController = getRootNavController()

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        observeViewModel(navController)
        onNavControllerActivated(navController)

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestination)
        )
    }

    private fun observeViewModel(navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavGraph())
        viewModel.userState.observe(this) { authState ->
            when (authState) {
                is AuthState.Success -> {
                    graph.setStartDestination(getTabsDestination())
                    navController.graph = graph
                }
                is AuthState.Error -> {
                    graph.setStartDestination(getSignInDestination())
                    navController.graph = graph
                }
            }
        }
    }

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return navHost.navController
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController.navigateUp()) || super.onSupportNavigateUp()

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController.removeOnDestinationChangedListener(destinationListener)
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestination + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    private fun getSignInDestination() = R.id.signInFragment
    private fun getTabsDestination() = R.id.tabsFragment
    private fun getMainNavGraph() = R.navigation.main_graph

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        super.onDestroy()
    }

    fun createCorrectQuiz(){

        val answersList = mutableListOf(
            QuizAnswers(
                description = "Kotlin",
                correctFlag = true
            ),
            QuizAnswers(
                description = "Dart",
                correctFlag = false
            ),
            QuizAnswers(
                description = "Python",
                correctFlag = false
            ),
            QuizAnswers(
                description = "C++",
                correctFlag = false
            ),
        )

        val questionList = mutableListOf(
            QuizQuestions(
                id = 0,
                questionText = "Какой язык программирования самый лучший?",
                answers = answersList
                ),
            QuizQuestions(
                id = 0,
                questionText = "Какой язык программирования самый лучший?",
                answers = answersList
            ),
            QuizQuestions(
                id = 0,
                questionText = "Какой язык программирования самый лучший?",
                answers = answersList
            ),
            QuizQuestions(
                id = 0,
                questionText = "Какой язык программирования самый лучший?",
                answers = answersList
            ),
            QuizQuestions(
                id = 0,
                questionText = "Какой язык программирования самый лучший?",
                answers = answersList
            ),
        )

        val quizList = mutableListOf(
            Quiz(
                id = 0,
                title = "Языки программирования",
                questions = questionList
            ),
            Quiz(
                id = 1,
                title = "Языки программирования",
                questions = questionList
            ),
            Quiz(
                id = 2,
                title = "Языки программирования",
                questions = questionList
            ),
            Quiz(
                id = 3,
                title = "Языки программирования",
                questions = questionList
            )
        )

        val dataRepository = DataRepository()

        quizList.forEach {
                dataRepository.pushQuiz(it).addOnSuccessListener {
                    Log.d("TAG", "Quiz push success")
                }
            }
    }
}