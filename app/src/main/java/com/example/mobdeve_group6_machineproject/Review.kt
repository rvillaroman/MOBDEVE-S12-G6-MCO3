package com.example.mobdeve_group6_machineproject

data class Review(
    val reviewId: Int = 0,
    val reviewTitle: String,
    val reviewRating: Float,
    val reviewInput: String,
    val userId: Int
)
