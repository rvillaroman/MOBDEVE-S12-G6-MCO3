package com.example.mobdeve_group6_machineproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class WriteReviewFragment : Fragment() {

    private lateinit var etReviewTitle: EditText
    private lateinit var etReviewBody: EditText
    private lateinit var btnSubmitReview: Button
    private lateinit var starLayout: ViewGroup
    private var rating: Int = 0
    private lateinit var dbOperations: DatabaseOperations

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etReviewTitle = view.findViewById(R.id.etReviewTitle)
        etReviewBody = view.findViewById(R.id.etReviewBody)
        btnSubmitReview = view.findViewById(R.id.btnSubmitReview)
        starLayout = view.findViewById(R.id.starLayout)
        dbOperations = DatabaseOperations(requireContext())

        // Handle star rating selection
        for (i in 0 until starLayout.childCount) {
            val star = starLayout.getChildAt(i) as TextView
            star.setOnClickListener {
                rating = i + 1
                updateStarRating()
            }
        }

        btnSubmitReview.setOnClickListener {
            val reviewTitle = etReviewTitle.text.toString()
            val reviewBody = etReviewBody.text.toString()
            val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("USERNAME", "Unknown User")
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (reviewTitle.isNotEmpty() && reviewBody.isNotEmpty() && rating > 0) {
                val review = Review(
                    reviewId = 0, // Assuming 0 will be auto-incremented by the database
                    reviewTitle = reviewTitle,
                    reviewRating = rating.toFloat(),
                    reviewInput = reviewBody,
                    userId = 1 // Replace with actual user ID
                )
                dbOperations.addReview(review)
                Toast.makeText(requireContext(), "Review submitted", Toast.LENGTH_SHORT).show()

                val reviewDetailsFragment = ReviewDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString("REVIEW_TITLE", reviewTitle)
                        putString("REVIEW_BODY", reviewBody)
                        putInt("REVIEW_RATING", rating)
                        putString("REVIEW_DATE", currentDate)
                        putString("REVIEW_USERNAME", username)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, reviewDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields and select a rating", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateStarRating() {
        for (i in 0 until starLayout.childCount) {
            val star = starLayout.getChildAt(i) as TextView
            if (i < rating) {
                star.text = "★"
            } else {
                star.text = "☆"
            }
        }
    }
}
