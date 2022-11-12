package com.example.simpleregistration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.simpleregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.customToolBar)
        val navController = findNavController(R.id.fragment_container_view)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.signInFragment,
                R.id.studentFragment,
                R.id.teacherFragment)),
        )
        binding.customToolBar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}