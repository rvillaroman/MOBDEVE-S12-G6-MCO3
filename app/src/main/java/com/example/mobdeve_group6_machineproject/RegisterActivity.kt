package com.example.mobdeve_group6_machineproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etBirthday: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var tvLogin: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.etFullName)
        etUsername = findViewById(R.id.etUsername)
        etBirthday = findViewById(R.id.etBirthday)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)
        tvLogin = findViewById(R.id.tvLogin)
        databaseHelper = DatabaseHelper(this)

        btnCreateAccount.setOnClickListener {
            val name = etName.text.toString()
            val username = etUsername.text.toString()
            val birthday = etBirthday.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (name.isNotEmpty() && username.isNotEmpty() && birthday.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                val userId = databaseHelper.addUser(name, username, birthday, password)
                if (userId > -1) {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields and make sure passwords match", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            finish()
        }
    }
}
