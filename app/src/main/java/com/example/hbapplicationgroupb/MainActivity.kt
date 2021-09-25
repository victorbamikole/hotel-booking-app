package com.example.hbapplicationgroupb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hbapplicationgroupb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //var of type of binding class created for xml file
    private lateinit var binding:ActivityMainBinding

    /**
     * Create an instance of the navigation controller
     */
    private lateinit var instanceOfNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//set binding to root layout

        /**
         * Create a variable to hold the bottom navigation container, and the fragmentContainerView
         */
        val bottomNav = binding.bottomNavContainerId

        val fragmentContainerView =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        instanceOfNavController = fragmentContainerView.navController

        /**
         * Set up bottom navigation with the navigation graph, such that if you click on the
         * profile bottom nav icon, it also takes you to the profile fragment view
         */
        bottomNav.setupWithNavController(instanceOfNavController)


    }
}