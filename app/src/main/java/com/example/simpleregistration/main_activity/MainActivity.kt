package com.example.simpleregistration.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.fragment_container_view) }
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory() }
    private val topLevelDestination = setOf(
        getTabsFragment(), getSignInDestination()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.customToolBar)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestination),
        )
        binding.customToolBar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.userState.observe(this) { authState ->
            when (authState) {
                is AuthState.Success -> {
                    prepareRootNavController(true)
                }
                is AuthState.Error -> {
                    prepareRootNavController(false)
                }
            }
        }
    }

    private fun prepareRootNavController(isSignIn: Boolean) {
        val graph = navController.navInflater.inflate(getMainNavGraph())
        graph.setStartDestination(if (isSignIn) getTabsFragment() else getSignInDestination())
        navController.graph = graph
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController.navigateUp()) || super.onSupportNavigateUp()

    private fun getSignInDestination() = R.id.signInFragment
    private fun getTabsFragment() = R.id.tabsFragment
    private fun getMainNavGraph() = R.navigation.main_graph
}