package com.example.hbapplicationgroupb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hbapplicationgroupb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //var of type of binding class created for xml file
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//set binding to root layout

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment

        binding.bottomNavContainerId.setupWithNavController(navController = navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener{ _, destination,_ ->
            when(destination.id){
                R.id.exploreFragment2 -> showNavBar()
                R.id.exploreFragment -> showNavBar()
                R.id.profileFragment -> showNavBar()
                R.id.wishListFragment -> showNavBar()
                else -> binding.bottomNavContainerId.visibility = View.GONE
            }
        }
    }

    private fun showNavBar() {
        binding.bottomNavContainerId.visibility = View.VISIBLE
    }

}
