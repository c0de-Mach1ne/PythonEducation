package com.example.simpleregistration.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simpleregistration.R
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { getRootNavController() }
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { MainViewModelFactory() }
    private val topLevelDestination = setOf(
        getTabsFragment(), getSignInDestination()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: придумать более лаконичный способ изменения статус бара на цвет темы
        window.statusBarColor = ContextCompat.getColor(this, R.color.black);
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(topLevelDestination),
        )
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

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return navHost.navController
    }

    private fun getSignInDestination() = R.id.signInFragment
    private fun getTabsFragment() = R.id.tabsFragment
    private fun getMainNavGraph() = R.navigation.main_graph

}