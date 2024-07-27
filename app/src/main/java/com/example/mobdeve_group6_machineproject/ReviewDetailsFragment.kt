package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ReviewDetailsFragment : Fragment() {

    private lateinit var llReviews: LinearLayout
    private lateinit var btnWriteReview: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llReviews = view.findViewById(R.id.llReviews)
        btnWriteReview = view.findViewById(R.id.btnWriteReview)

        arguments?.let {
            val reviewTitle = it.getString("REVIEW_TITLE")
            val reviewBody = it.getString("REVIEW_BODY")
            val rating = it.getInt("REVIEW_RATING")
            val reviewDate = it.getString("REVIEW_DATE")
            val reviewUsername = it.getString("REVIEW_USERNAME")

            addReviewToLayout(reviewTitle, reviewBody, rating, reviewDate, reviewUsername)
        }

        btnWriteReview.setOnClickListener {
            navigateToWriteReview()
        }
    }

    private fun addReviewToLayout(title: String?, body: String?, rating: Int, date: String?, username: String?) {
        val reviewView = LayoutInflater.from(context).inflate(R.layout.item_review, llReviews, false)
        val tvReviewerName: TextView = reviewView.findViewById(R.id.tvReviewerName1)
        val tvReviewDate: TextView = reviewView.findViewById(R.id.tvReviewDate1)
        val tvReviewText: TextView = reviewView.findViewById(R.id.tvReviewText1)
        val tvReviewRating: TextView = reviewView.findViewById(R.id.tvReviewRating1)

        tvReviewerName.text = username ?: "Unknown User"
        tvReviewDate.text = date ?: "Unknown Date"
        tvReviewText.text = body
        tvReviewRating.text = "★".repeat(rating)

        llReviews.addView(reviewView)
    }

    private fun navigateToWriteReview() {
        val writeReviewFragment = WriteReviewFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, writeReviewFragment)
            .addToBackStack(null)
            .commit()
    }
}
