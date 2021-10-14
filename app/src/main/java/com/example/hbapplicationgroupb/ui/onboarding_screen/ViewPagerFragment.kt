package com.example.hbapplicationgroupb.ui.onboarding_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentViewPagerBinding
import com.example.hbapplicationgroupb.ui.onboarding_screen.adapter.OnBoardingItemAdapter
import com.example.hbapplicationgroupb.ui.onboarding_screen.model.OnBoardingItem


class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnBoardingItem()
        setUpIndicator()
        setUpCurrentIndicator(0)
    }

   private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }


    private fun navigateToLoginFragment() {
      findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        onBoardingFinished()
    }


    private fun setUpOnBoardingItem() {
        onBoardingItemAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.onboarding_01_img,
                    title = "Search and save your preferences",
                    description = "browse best hotels from 40,000+ database that fits your unique needs"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.onboarding_02_img,
                    title = "Find the best deals",
                    description = "Find the best deals from any season and book from a curated list"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.onboarding_03,
                    title = "Book and enjoy your stay",
                    description = "select hotel and date as per your preference to book and have a pleasant stay"
                )
            )
        )

        binding.onboardingViewPager.adapter = onBoardingItemAdapter
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setUpCurrentIndicator(position)
            }
        })
        (binding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        val onBoardingVp = binding.onboardingViewPager

        binding.btnNext.setOnClickListener {
            if (onBoardingVp.currentItem +1 < onBoardingItemAdapter.itemCount){
                onBoardingVp.currentItem +=1

            }else{

                navigateToLoginFragment()
                onBoardingFinished()
            }
        }
        binding.btnSkip.setOnClickListener {
            navigateToLoginFragment()
            onBoardingFinished()
        }
    }

    private fun restoreButtonText() {
        binding.btnNext.text = getString(R.string.next)
        binding.btnSkip.text = getString(R.string.skip)
    }


    private fun setUpIndicator() {
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParam : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParam.setMargins(8,0,8,0)


        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.let {
                it.setImageDrawable(
                    context?.let { it1 ->
                        ContextCompat.getDrawable(
                            it1,
                            R.drawable.unselected_circle
                        )
                    }
                )
                it.layoutParams = layoutParam
                binding.indicatorContainer.addView(it)
            }
        }
    }



    private fun changeNextAndSkipButtonText() {
        binding.btnNext.text = getString(R.string.getStarted)
        binding.btnSkip.text = getString(R.string.done)
    }

    fun setUpCurrentIndicator(position: Int) {
        if(position == onBoardingItemAdapter.itemCount -1) {
            changeNextAndSkipButtonText()
        }else{
            restoreButtonText()
        }
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer.getChildAt(i) as ImageView
            if (i==position){
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.selected_background
                        )
                    }
                )
            }else{
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.unselected_circle
                        )
                    }
                )
            }
        }
    }
}