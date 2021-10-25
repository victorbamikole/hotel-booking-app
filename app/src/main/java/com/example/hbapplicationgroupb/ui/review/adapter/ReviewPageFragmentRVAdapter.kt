package com.example.hbapplicationgroupb.ui.review.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.UserReview
import com.example.hbapplicationgroupb.model.addCustomerRating.hotelRating.HotelRating

class ReviewPageFragmentRVAdapter:
    RecyclerView.Adapter<ReviewPageFragmentRVAdapter.ReviewPageFragmentViewHolder>() {
    //list of items recycler view
//    private val listOfReview:MutableList<UserReview> = mutableListOf()
    private val listOfReview:ArrayList<HotelRating> = arrayListOf()

    //internal class for holding recycler layout views together
    inner class ReviewPageFragmentViewHolder(view: View):RecyclerView.ViewHolder(view){
        //reviewers profile image view
        val profileImage:ImageView = view
            .findViewById(R.id.fragment_review_page_recycler_imageView_personProfile)
        //reviewers profile name view
        val profileName:TextView = view
            .findViewById(R.id.fragment_review_page_recycler_tv_personName)
        //reviewers written review view
        val review:TextView = view
            .findViewById(R.id.fragment_review_page_recycler_tv_userReview)
        val rating : RatingBar = view.findViewById(R.id.fragment_review_page1_star_view_ratingBar1)
    }

    //function to hold views together after it has been created
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewPageFragmentViewHolder {
        //inflate recycler view layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_review_page_recycler_view_layout,parent,false)
        return ReviewPageFragmentViewHolder(view)
    }

    //function to set data to each views
    override fun onBindViewHolder(holder: ReviewPageFragmentViewHolder, position: Int) {
        val currentReview = listOfReview[position]
        holder.apply {
            Glide.with(itemView)
                .load(currentReview.image)
                .into(profileImage)
            profileName.text = currentReview.name
            review.text = currentReview.review
            rating.rating = currentReview.rating.toFloat()
        }
    }

    //function to get total number of items on the list
    override fun getItemCount(): Int = listOfReview.size

    //function to get all list from an external source
    fun getListOfReviews(reviews:ArrayList<HotelRating>){
        listOfReview.clear()
        listOfReview.addAll(reviews)
        notifyDataSetChanged()
    }

}