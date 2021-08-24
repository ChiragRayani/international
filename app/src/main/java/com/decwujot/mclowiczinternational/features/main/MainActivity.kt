package com.decwujot.mclowiczinternational.features.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.decwujot.mclowiczinternational.R
import com.decwujot.mclowiczinternational.databinding.ActivityMainBinding
import com.decwujot.mclowiczinternational.features.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.homeFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }
}