package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvGreeting: TextView = view.findViewById(R.id.tvGreeting)
        val tvViewMoreDetails1: TextView = view.findViewById(R.id.tvViewMoreDetails1)
        val tvViewMoreDetails2: TextView = view.findViewById(R.id.tvViewMoreDetails2)
        val tvViewMoreDetails3: TextView = view.findViewById(R.id.tvViewMoreDetails3)

        // Retrieve the username from the arguments
        val username = arguments?.getString("USERNAME")
        tvGreeting.text = "Welcome, $username"

        tvViewMoreDetails1.setOnClickListener {
            loadMovieDetailFragment()
        }

        tvViewMoreDetails2.setOnClickListener {
            loadMovieDetailFragment()
        }

        tvViewMoreDetails3.setOnClickListener {
            loadMovieDetailFragment()
        }
    }

    private fun loadMovieDetailFragment() {
        val fragment = MovieDetailFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
