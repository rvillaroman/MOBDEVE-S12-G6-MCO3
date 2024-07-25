package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Button

class WriteReviewFragment : Fragment() {

    private var selectedRating = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvBackToReviews).setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        val stars = listOf(
            view.findViewById<TextView>(R.id.star1),
            view.findViewById<TextView>(R.id.star2),
            view.findViewById<TextView>(R.id.star3),
            view.findViewById<TextView>(R.id.star4),
            view.findViewById<TextView>(R.id.star5)
        )

        stars.forEachIndexed { index, star ->
            star.setOnClickListener {
                setRating(index + 1, stars)
            }
        }

        view.findViewById<Button>(R.id.btnSubmitReview).setOnClickListener {
            val reviewTitle = view.findViewById<EditText>(R.id.etReviewTitle).text.toString()
            val reviewBody = view.findViewById<EditText>(R.id.etReviewBody).text.toString()

            // Handle review submission logic here (e.g., save to database, send to server, etc.)

            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setRating(rating: Int, stars: List<TextView>) {
        selectedRating = rating
        for (i in 0 until rating) {
            stars[i].text = "★"
        }
        for (i in rating until stars.size) {
            stars[i].text = "☆"
        }
    }
}
