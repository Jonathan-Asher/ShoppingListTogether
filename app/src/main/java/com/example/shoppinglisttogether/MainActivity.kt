package com.example.shoppinglisttogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Get NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        
        // Define top-level destinations
        // This will prevent showing the Up button in these fragments
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.loginFragment, R.id.registerFragment)
        )

        // Set up the bottom navigation view with the nav controller
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setupWithNavController(navController)
        
        // Link the ActionBar with NavController (optional)
        // setupActionBarWithNavController(navController, appBarConfiguration)
        
        // Listen for navigation changes to update the bottom nav selection and visibility
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Show/hide bottom navigation based on the destination
            when (destination.id) {
                R.id.homeFragment -> {
                    bottomNavigationView.visibility = android.view.View.VISIBLE
                }
                R.id.loginFragment, R.id.registerFragment -> {
                    // Hide bottom navigation on login/register screens
                    bottomNavigationView.visibility = android.view.View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
