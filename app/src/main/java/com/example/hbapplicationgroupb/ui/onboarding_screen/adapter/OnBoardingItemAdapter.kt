package com.example.hbapplicationgroupb.ui.onboarding_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.onboarding_screen.model.OnBoardingItem

class OnBoardingItemAdapter(private val onBoardingItem: List<OnBoardingItem>) :RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>(){

    inner class OnBoardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.onboardingImage)
        private val titleOnBoarding = view.findViewById<TextView>(R.id.onboardingTitle)
        private val descriptionOnBoarding = view.findViewById<TextView>(R.id.onboardingDescription)

        fun bind(onBoardingItem: OnBoardingItem){
            imageOnBoarding.setImageResource(onBoardingItem.onBoardingImage)
            titleOnBoarding.text = onBoardingItem.title
            descriptionOnBoarding.text =onBoardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_pager_template,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItem.size
    }
}