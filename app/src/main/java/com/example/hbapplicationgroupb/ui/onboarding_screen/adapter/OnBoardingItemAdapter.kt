package com.example.hbapplicationgroupb.ui.onboarding_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.onboarding_screen.model.OnboardingItem

class OnBoardingItemAdapter(private val onboardingItem: List<OnboardingItem>) :RecyclerView.Adapter<OnBoardingItemAdapter.OnboardingItemViewHolder>(){

    inner class OnboardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val imageOnboarding = view.findViewById<ImageView>(R.id.onboardingImage)
        private val titleOnboarding = view.findViewById<TextView>(R.id.onboardingTitle)
        private val descriptionOnboarding = view.findViewById<TextView>(R.id.onboardingDescription)

        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            titleOnboarding.text = onboardingItem.title
            descriptionOnboarding.text =onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_pager_template,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItem[position])
    }

    override fun getItemCount(): Int {
        return onboardingItem.size
    }
}