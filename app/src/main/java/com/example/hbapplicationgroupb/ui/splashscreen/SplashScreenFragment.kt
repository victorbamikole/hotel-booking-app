package com.example.hbapplicationgroupb

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.databinding.FragmentSplashScreenBinding
import com.google.android.material.animation.AnimationUtils

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hotelTextViewAnimation = android.view.animation.AnimationUtils.loadAnimation(requireContext(),R.anim.slide_from_top_animation)
        val voyageTextViewAnimation = android.view.animation.AnimationUtils.loadAnimation(requireContext(),R.anim.slide_from_bottom_animation)

        binding.hotelTv.startAnimation(hotelTextViewAnimation)
        binding.voyageTv.startAnimation(voyageTextViewAnimation)
        
        val splashScreenTimeout = 5000
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_onboarding_01Fragment)
            activity?.finish()
        },splashScreenTimeout.toLong())

    }


}