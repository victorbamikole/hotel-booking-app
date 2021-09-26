package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentBottomNavigationContainerBinding


class BottomNavigationFragmentContainer : Fragment(R.layout.fragment_bottom_navigation_container) {
    private lateinit var binding: FragmentBottomNavigationContainerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomNavigationContainerBinding.bind(view)

        /**
         * find bottom nav container
         */
        val bottomNav = binding.bottomNavContainerId

        /**
         * Find navigation controller
         */
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        /**
         * Set bottom nav with nav container
         */

        bottomNav.setupWithNavController(navController = navHostFragment.navController)
    }

}