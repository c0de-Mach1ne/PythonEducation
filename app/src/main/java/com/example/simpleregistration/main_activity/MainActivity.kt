package com.example.simpleregistration.main_activity

import android.os.Bundle
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
import com.example.simpleregistration.auth.AuthState
import com.example.simpleregistration.databinding.ActivityMainBinding
import com.example.simpleregistration.fragments.TabsFragment

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
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

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.toolbar)

        // TODO: придумать более лаконичный способ изменения статус бара на цвет темы
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val navController = getRootNavController()

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
                }
                is AuthState.Error -> {
                    graph.setStartDestination(getSignInDestination())
                }
            }
        }
        navController.graph = graph
    }

    private fun getRootNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return navHost.navController
    }

    override fun onSupportNavigateUp(): Boolean =
        (navController?.navigateUp() ?: false) || super.onSupportNavigateUp()

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController?.removeOnDestinationChangedListener(destinationListener)
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestination + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        super.onDestroy()
    }

    private fun getSignInDestination() = R.id.signInFragment
    private fun getTabsDestination() = R.id.tabsFragment
    private fun getMainNavGraph() = R.navigation.main_graph
}