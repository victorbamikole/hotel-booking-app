package com.example.hbapplicationgroupb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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

        val fragmentContainerView = Navigation.findNavController(binding.fragmentContainerView2)

        bottomNav.setupWithNavController(fragmentContainerView)
    }

}