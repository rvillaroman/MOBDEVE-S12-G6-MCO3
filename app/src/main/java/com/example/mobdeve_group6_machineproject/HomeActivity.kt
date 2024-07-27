package com.example.mobdeve_group6_machineproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("USERNAME", null) ?: run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_profile -> selectedFragment = ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString("USERNAME", username)
                    }
                }
                R.id.nav_nearby -> selectedFragment = NearbyFragment().apply {
                    arguments = Bundle().apply {
                        putString("USERNAME", username)
                    }
                }
                R.id.nav_book -> selectedFragment = BookFragment().apply {
                    arguments = Bundle().apply {
                        putString("USERNAME", username)
                    }
                }
                R.id.nav_movie_list -> selectedFragment = MovieListFragment().apply {
                    arguments = Bundle().apply {
                        putString("USERNAME", username)
                    }
                }
                R.id.nav_review -> selectedFragment = ReviewFragment().apply {
                    arguments = Bundle().apply {
                        putString("USERNAME", username)
                    }
                }
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            true
        }

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.nav_profile
        }
    }
}
