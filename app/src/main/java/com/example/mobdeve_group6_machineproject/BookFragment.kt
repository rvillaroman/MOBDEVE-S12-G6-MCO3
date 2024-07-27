package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment

class BookFragment : Fragment() {

    private var maxSelectableSeats = 1
    private var selectedSeatsCount = 0

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
        val gridSeats: GridLayout = view.findViewById(R.id.gridSeats)
        val btnProceedToPayment: Button = view.findViewById(R.id.btnProceedToPayment)

        // Dummy data for spinners
        val movies = arrayOf("Inside Out 2", "The Exorcism", "Furiosa")
        val locations = arrayOf("SM Aura Premier", "SM Mall of Asia", "SM North EDSA")
        val times = arrayOf("10:00 AM", "01:00 PM", "04:00 PM", "07:00 PM")
        val dates = arrayOf("2024-07-23", "2024-07-24", "2024-07-25")
        val tickets = arrayOf("1 Ticket", "2 Tickets", "3 Tickets", "4 Tickets")

        spinnerMovie.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, movies)
        spinnerLocation.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, locations)
        spinnerTime.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, times)
        spinnerDate.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, dates)
        spinnerTickets.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, tickets)

        spinnerTickets.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: View?, position: Int, id: Long) {
                maxSelectableSeats = position + 1
                resetSeatSelection(gridSeats)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }

        // Handle seat selection
        for (i in 0 until gridSeats.childCount) {
            val button = gridSeats.getChildAt(i) as Button
            button.setOnClickListener {
                if (button.isSelected) {
                    button.isSelected = false
                    button.setBackgroundColor(android.graphics.Color.LTGRAY)
                    selectedSeatsCount--
                } else if (selectedSeatsCount < maxSelectableSeats) {
                    button.isSelected = true
                    button.setBackgroundColor(android.graphics.Color.RED)
                    selectedSeatsCount++
                } else {
                    Toast.makeText(requireContext(), "You can only select up to $maxSelectableSeats seats", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnProceedToPayment.setOnClickListener {
            // Handle proceed to payment
        }
    }

    private fun resetSeatSelection(gridSeats: GridLayout) {
        for (i in 0 until gridSeats.childCount) {
            val button = gridSeats.getChildAt(i) as Button
            button.isSelected = false
            button.setBackgroundColor(android.graphics.Color.LTGRAY)
        }
        selectedSeatsCount = 0
    }
}
