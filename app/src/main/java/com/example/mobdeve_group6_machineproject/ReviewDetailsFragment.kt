package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button

class ReviewDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieTitle = arguments?.getString("movieTitle")
        val movieDetails = arguments?.getString("movieDetails")
        val movieRating = arguments?.getDouble("movieRating")
        val movieDescription = arguments?.getString("movieDescription")
        val moviePoster = arguments?.getInt("moviePoster")

        view.findViewById<TextView>(R.id.tvMovieTitle).text = movieTitle
        view.findViewById<TextView>(R.id.tvMovieDetails).text = movieDetails
        view.findViewById<TextView>(R.id.tvMovieRating).text = "★ $movieRating"
        view.findViewById<TextView>(R.id.tvMovieDescription).text = movieDescription
        view.findViewById<ImageView>(R.id.ivMoviePoster).setImageResource(moviePoster ?: R.drawable.ic_insideout2) // Replace with the actual default image if needed

        view.findViewById<TextView>(R.id.tvBackToList).setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        view.findViewById<Button>(R.id.btnWriteReview).setOnClickListener {
            val fragment = WriteReviewFragment()
            val bundle = Bundle().apply {
                putString("movieTitle", movieTitle)
            }
            fragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}
