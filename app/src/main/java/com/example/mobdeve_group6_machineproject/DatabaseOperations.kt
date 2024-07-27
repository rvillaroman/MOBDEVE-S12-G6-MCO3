package com.example.mobdeve_group6_machineproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DatabaseOperations(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    // User Operations
    fun addUser(user: User) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.USER_NAME, user.userName)
            put(DatabaseHelper.USER_USERNAME, user.userUsername)
            put(DatabaseHelper.USER_BIRTHDAY, user.userBirthday)
            put(DatabaseHelper.USER_PASSWORD, user.userPassword)
        }

        db.insert(DatabaseHelper.USER_TABLE, null, values)
        db.close()
    }

    fun getAllUsers(): List<User> {
        val userList = ArrayList<User>()
        val selectQuery = "SELECT * FROM ${DatabaseHelper.USER_TABLE}"
        val db = dbHelper.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID)),
                    userName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME)),
                    userUsername = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_USERNAME)),
                    userBirthday = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_BIRTHDAY)),
                    userPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PASSWORD))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

    fun updateUser(user: User): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.USER_NAME, user.userName)
            put(DatabaseHelper.USER_USERNAME, user.userUsername)
            put(DatabaseHelper.USER_BIRTHDAY, user.userBirthday)
            put(DatabaseHelper.USER_PASSWORD, user.userPassword)
        }

        return db.update(
            DatabaseHelper.USER_TABLE,
            values,
            "${DatabaseHelper.USER_ID} = ?",
            arrayOf(user.userId.toString())
        )
    }

    fun deleteUser(user: User) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.USER_TABLE,
            "${DatabaseHelper.USER_ID} = ?",
            arrayOf(user.userId.toString())
        )
        db.close()
    }

    // Movie Operations
    fun addMovie(movie: Movies) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.MOVIE_NAME, movie.movieName)
            put(DatabaseHelper.MOVIE_RATING, movie.movieRating)
            put(DatabaseHelper.MOVIE_RELEASE_DATE, movie.movieReleaseDate)
            put(DatabaseHelper.MOVIE_DURATION, movie.movieDuration)
            put(DatabaseHelper.MOVIE_DESCRIPTION, movie.movieDescription)
            put(DatabaseHelper.MOVIE_CAST, movie.movieCast)
            put(DatabaseHelper.MOVIE_DIRECTOR, movie.movieDirector)
        }

        db.insert(DatabaseHelper.MOVIE_TABLE, null, values)
        db.close()
    }

    fun getAllMovies(): List<Movies> {
        val movieList = ArrayList<Movies>()
        val selectQuery = "SELECT * FROM ${DatabaseHelper.MOVIE_TABLE}"
        val db = dbHelper.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val movie = Movies().apply {
                    movieId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_ID))
                    movieName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_NAME))
                    movieRating = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_RATING))
                    movieReleaseDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_RELEASE_DATE))
                    movieDuration = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DURATION))
                    movieDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DESCRIPTION))
                    movieCast = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_CAST))
                    movieDirector = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DIRECTOR))
                }
                movieList.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return movieList
    }

    fun updateMovie(movie: Movies): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.MOVIE_NAME, movie.movieName)
            put(DatabaseHelper.MOVIE_RATING, movie.movieRating)
            put(DatabaseHelper.MOVIE_RELEASE_DATE, movie.movieReleaseDate)
            put(DatabaseHelper.MOVIE_DURATION, movie.movieDuration)
            put(DatabaseHelper.MOVIE_DESCRIPTION, movie.movieDescription)
            put(DatabaseHelper.MOVIE_CAST, movie.movieCast)
            put(DatabaseHelper.MOVIE_DIRECTOR, movie.movieDirector)
        }

        return db.update(
            DatabaseHelper.MOVIE_TABLE,
            values,
            "${DatabaseHelper.MOVIE_ID} = ?",
            arrayOf(movie.movieId.toString())
        )
    }

    fun deleteMovie(movie: Movies) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.MOVIE_TABLE,
            "${DatabaseHelper.MOVIE_ID} = ?",
            arrayOf(movie.movieId.toString())
        )
        db.close()
    }

    // Review Operations
    fun addReview(review: Review) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.REVIEW_TITLE, review.reviewTitle)
            put(DatabaseHelper.REVIEW_RATING, review.reviewRating)
            put(DatabaseHelper.REVIEW_INPUT, review.reviewInput)
            put(DatabaseHelper.REVIEW_USER_ID, review.userId)
        }

        db.insert(DatabaseHelper.REVIEW_TABLE, null, values)
        db.close()
    }

    fun getAllReview(): List<Review> {
        val reviewList = ArrayList<Review>()
        val selectQuery = "SELECT * FROM ${DatabaseHelper.REVIEW_TABLE}"
        val db = dbHelper.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val review = Review(
                    reviewId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_ID)),
                    reviewTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_TITLE)),
                    reviewRating = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_RATING)),
                    reviewInput = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_INPUT)),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_USER_ID))
                )
                reviewList.add(review)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return reviewList
    }

    fun updateReview(review: Review): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.REVIEW_TITLE, review.reviewTitle)
            put(DatabaseHelper.REVIEW_RATING, review.reviewRating)
            put(DatabaseHelper.REVIEW_INPUT, review.reviewInput)
            put(DatabaseHelper.REVIEW_USER_ID, review.userId)
        }

        return db.update(
            DatabaseHelper.REVIEW_TABLE,
            values,
            "${DatabaseHelper.REVIEW_ID} = ?",
            arrayOf(review.reviewId.toString())
        )
    }

    fun deleteReview(review: Review) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.REVIEW_TABLE,
            "${DatabaseHelper.REVIEW_ID} = ?",
            arrayOf(review.reviewId.toString())
        )
        db.close()
    }
}
