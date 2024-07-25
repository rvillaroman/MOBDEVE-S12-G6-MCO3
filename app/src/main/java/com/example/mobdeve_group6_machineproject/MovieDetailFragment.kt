package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvBackToList: TextView = view.findViewById(R.id.tvBackToList)
        val btnBookNow: Button = view.findViewById(R.id.btnBookNow)

        tvBackToList.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnBookNow.setOnClickListener {
            // Handle booking functionality here
        }
    }
}
