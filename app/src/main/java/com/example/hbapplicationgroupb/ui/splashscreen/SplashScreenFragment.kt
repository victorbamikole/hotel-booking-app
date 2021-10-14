package com.example.hbapplicationgroupb.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater,container,false)

        setUpSplashScreen()
        // Inflate the layout for this fragment
        return binding.root
    }


    private fun setUpSplashScreen() {
        val hotelTextViewAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_from_top_animation
        )
        val voyageTextViewAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_from_bottom_animation
        )

        binding.hotelTv.startAnimation(hotelTextViewAnimation)
        binding.voyageTv.startAnimation(voyageTextViewAnimation)

        val splashScreenTimeout = 3500
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            } else {

            findNavController().navigate(
                R.id.action_splashScreenFragment_to_viewPagerFragment, null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.splashScreenFragment, true).build()
            )}
        }, splashScreenTimeout.toLong())
    }

    private fun onBoardingFinished():Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onboarding",Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
          }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}