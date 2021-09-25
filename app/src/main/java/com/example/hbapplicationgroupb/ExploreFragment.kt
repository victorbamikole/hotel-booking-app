package com.example.hbapplicationgroupb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.databinding.FragmentExploreBinding

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    var binding : FragmentExploreBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

}