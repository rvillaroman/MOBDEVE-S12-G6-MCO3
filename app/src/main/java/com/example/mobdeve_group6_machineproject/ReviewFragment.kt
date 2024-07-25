package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ReviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieReviewButton1 = view.findViewById<TextView>(R.id.tvViewMovieReviews1)
        val movieReviewButton2 = view.findViewById<TextView>(R.id.tvViewMovieReviews2)
        val movieReviewButton3 = view.findViewById<TextView>(R.id.tvViewMovieReviews3)

        movieReviewButton1.setOnClickListener {
            navigateToReviewDetails("Inside Out 2", "2024 | 1hr 36m", 4.5, "Journey back into the mind of Riley...", R.drawable.ic_insideout2)
        }

        movieReviewButton2.setOnClickListener {
            navigateToReviewDetails("The Exorcism", "2024 | 1hr 33m", 4.2, "A troubled actor begins to unravel...", R.drawable.ic_theexorcism)
        }

        movieReviewButton3.setOnClickListener {
            navigateToReviewDetails("Furiosa", "2024 | 2hr 28m", 4.8, "Snatched from the Green Place...", R.drawable.ic_furiosa)
        }
    }

    private fun navigateToReviewDetails(title: String, details: String, rating: Double, description: String, poster: Int) {
        val fragment = ReviewDetailsFragment()
        val bundle = Bundle().apply {
            putString("movieTitle", title)
            putString("movieDetails", details)
            putDouble("movieRating", rating)
            putString("movieDescription", description)
            putInt("moviePoster", poster)
        }
        fragment.arguments = bundle

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
