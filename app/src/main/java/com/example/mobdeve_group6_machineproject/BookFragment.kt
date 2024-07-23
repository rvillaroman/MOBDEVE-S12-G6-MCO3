package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment

class BookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerMovie: Spinner = view.findViewById(R.id.spinnerMovie)
        val spinnerLocation: Spinner = view.findViewById(R.id.spinnerLocation)
        val spinnerTime: Spinner = view.findViewById(R.id.spinnerTime)
        val spinnerDate: Spinner = view.findViewById(R.id.spinnerDate)
        val spinnerTickets: Spinner = view.findViewById(R.id.spinnerTickets)

        // Dummy data for spinners
        val movies = arrayOf("Movie 1", "Movie 2", "Movie 3")
        val locations = arrayOf("Location 1", "Location 2", "Location 3")
        val times = arrayOf("10:00 AM", "01:00 PM", "04:00 PM", "07:00 PM")
        val dates = arrayOf("2024-07-23", "2024-07-24", "2024-07-25")
        val tickets = arrayOf("1 Ticket", "2 Tickets", "3 Tickets", "4 Tickets")

        spinnerMovie.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, movies)
        spinnerLocation.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, locations)
        spinnerTime.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, times)
        spinnerDate.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, dates)
        spinnerTickets.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, tickets)

        // Handle seat selection
        val gridSeats: GridLayout = view.findViewById(R.id.gridSeats)
        for (i in 0 until gridSeats.childCount) {
            val button = gridSeats.getChildAt(i) as Button
            button.setOnClickListener {
                button.isSelected = !button.isSelected
                button.setBackgroundColor(
                    if (button.isSelected) android.graphics.Color.RED else android.graphics.Color.LTGRAY
                )
            }
        }

        val btnProceedToPayment: Button = view.findViewById(R.id.btnProceedToPayment)
        btnProceedToPayment.setOnClickListener {
            // Handle proceed to payment
        }
    }
}
