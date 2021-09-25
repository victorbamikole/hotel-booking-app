package com.example.hbapplicationgroupb.ui.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.UserReview

class ReviewPageFragmentRVAdapter:
    RecyclerView.Adapter<ReviewPageFragmentRVAdapter.ReviewPageFragmentViewHolder>() {
    //list of items recycler view
    private val listOfReview:MutableList<UserReview> = mutableListOf()

    //internal class for holding recycler layout views together
    inner class ReviewPageFragmentViewHolder(view: View):RecyclerView.ViewHolder(view){
        val profileImage:ImageView = view
            .findViewById(R.id.fragment_review_page_recycler_imageView_personProfile)
        val profileName:TextView = view
            .findViewById(R.id.fragment_review_page_recycler_tv_personName)
        val review:TextView = view
            .findViewById(R.id.fragment_review_page_recycler_tv_userReview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewPageFragmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_review_page_recycler_view_layout,parent,false)
        return ReviewPageFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewPageFragmentViewHolder, position: Int) {
        val listOfReviewPosition = listOfReview[position]
        holder.profileImage.setBackgroundResource(listOfReviewPosition.image)
        holder.profileName.text = listOfReviewPosition.name
        holder.review.text = listOfReviewPosition.review
    }

    override fun getItemCount(): Int = listOfReview.size

    fun getListOfReviews(listOf:List<UserReview>){
        listOfReview.clear()
        listOfReview.addAll(listOf)
        notifyDataSetChanged()
    }

}