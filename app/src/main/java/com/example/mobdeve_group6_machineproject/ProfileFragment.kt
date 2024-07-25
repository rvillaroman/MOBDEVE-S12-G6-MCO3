package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val ivProfilePicture: ImageView = view.findViewById(R.id.ivProfilePicture)
        val btnAccountSettings: Button = view.findViewById(R.id.btnAccountSettings)
        val btnAppSettings: Button = view.findViewById(R.id.btnAppSettings)
        val btnFeedback: Button = view.findViewById(R.id.btnFeedback)
        val btnContactUs: Button = view.findViewById(R.id.btnContactUs)
        val btnLogout: Button = view.findViewById(R.id.btnLogout)
        val ivNotificationIcon: ImageView = view.findViewById(R.id.ivNotificationIcon)

        // Set placeholder values
        tvUsername.text = "usernameTest"
        ivProfilePicture.setImageResource(R.drawable.ic_profile)

        // Handle button clicks
        btnAccountSettings.setOnClickListener {
            // Handle Account Settings click
        }

        btnAppSettings.setOnClickListener {
            // Handle App Settings click
        }

        btnFeedback.setOnClickListener {
            // Handle Feedback click
        }

        btnContactUs.setOnClickListener {
            // Handle Contact Us click
        }

        btnLogout.setOnClickListener {
            // Handle Log Out click
        }

        ivNotificationIcon.setOnClickListener {
            // Replace ProfileFragment with NotificationsFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotificationsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
