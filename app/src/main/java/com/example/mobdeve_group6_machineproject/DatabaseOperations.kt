package com.example.mobdeve_group6_machineproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.mobdeve_group6_machineproject.Movies
import com.example.mobdeve_group6_machineproject.User
import com.example.mobdeve_group6_machineproject.Reviews

class DatabaseOperations(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    // User Operations
    fun addUser(user: User) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHelper.USER_NAME, user.userName)
        values.put(DatabaseHelper.USER_USERNAME, user.userUsername)
        values.put(DatabaseHelper.USER_BIRTHDAY, user.userBirthday)
        values.put(DatabaseHelper.USER_PASSWORD, user.userPassword)

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
                val user = User()
                user.userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID))
                user.userName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_NAME))
                user.userUsername = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_USERNAME))
                user.userBirthday = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_BIRTHDAY))
                user.userPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PASSWORD))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

    fun updateUser(user: User): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHelper.USER_NAME, user.userName)
        values.put(DatabaseHelper.USER_USERNAME, user.userUsername)
        values.put(DatabaseHelper.USER_BIRTHDAY, user.userBirthday)
        values.put(DatabaseHelper.USER_PASSWORD, user.userPassword)

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
        val values = ContentValues()
        values.put(DatabaseHelper.MOVIE_NAME, movie.movieName)
        values.put(DatabaseHelper.MOVIE_RATING, movie.movieRating)
        values.put(DatabaseHelper.MOVIE_RELEASE_DATE, movie.movieReleaseDate)
        values.put(DatabaseHelper.MOVIE_DURATION, movie.movieDuration)
        values.put(DatabaseHelper.MOVIE_DESCRIPTION, movie.movieDescription)
        values.put(DatabaseHelper.MOVIE_CAST, movie.movieCast)
        values.put(DatabaseHelper.MOVIE_DIRECTOR, movie.movieDirector)

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
                val movie = Movies()
                movie.movieId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_ID))
                movie.movieName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_NAME))
                movie.movieRating = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_RATING))
                movie.movieReleaseDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_RELEASE_DATE))
                movie.movieDuration = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DURATION))
                movie.movieDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DESCRIPTION))
                movie.movieCast = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_CAST))
                movie.movieDirector = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MOVIE_DIRECTOR))
                movieList.add(movie)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return movieList
    }

    fun updateMovie(movie: Movies): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHelper.MOVIE_NAME, movie.movieName)
        values.put(DatabaseHelper.MOVIE_RATING, movie.movieRating)
        values.put(DatabaseHelper.MOVIE_RELEASE_DATE, movie.movieReleaseDate)
        values.put(DatabaseHelper.MOVIE_DURATION, movie.movieDuration)
        values.put(DatabaseHelper.MOVIE_DESCRIPTION, movie.movieDescription)
        values.put(DatabaseHelper.MOVIE_CAST, movie.movieCast)
        values.put(DatabaseHelper.MOVIE_DIRECTOR, movie.movieDirector)

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

    fun addReview(review: Reviews) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHelper.REVIEW_TITLE, review.reviewTitle)
        values.put(DatabaseHelper.REVIEW_RATING, review.reviewRating)
        values.put(DatabaseHelper.REVIEW_INPUT, review.reviewInput)
        values.put(DatabaseHelper.REVIEW_USER_ID, review.userId)

        db.insert(DatabaseHelper.REVIEW_TABLE, null, values)
        db.close()
    }

    fun getAllReview(): List<Reviews> {
        val reviewList = ArrayList<Reviews>()
        val selectQuery = "SELECT * FROM ${DatabaseHelper.REVIEW_TABLE}"
        val db = dbHelper.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val review = Reviews()
                review.userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID))
                review.reviewTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_TITLE))
                review.reviewRating = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_RATING))
                review.reviewInput = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_INPUT))
                review.reviewId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.REVIEW_ID))

                reviewList.add(review)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return reviewList
    }

    fun updateReview(review: Reviews): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHelper.REVIEW_TITLE, review.reviewTitle)
        values.put(DatabaseHelper.REVIEW_RATING, review.reviewRating)
        values.put(DatabaseHelper.REVIEW_INPUT, review.reviewInput)
        values.put(DatabaseHelper.REVIEW_USER_ID, review.userId)

        return db.update(
            DatabaseHelper.REVIEW_TABLE,
            values,
            "${DatabaseHelper.REVIEW_ID} = ?",
            arrayOf(review.reviewId.toString())
        )
    }

    fun deleteReview(review: Reviews) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.REVIEW_TABLE,
            "${DatabaseHelper.REVIEW_ID} = ?",
            arrayOf(review.reviewId.toString())
        )
        db.close()
    }
}