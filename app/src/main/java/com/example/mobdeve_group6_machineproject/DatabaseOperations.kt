package com.example.mobdeve_group6_machineproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.mobdeve_group6_machineproject.Movies
import com.example.mobdeve_group6_machineproject.User

class DatabaseOperations(context: Context) {
    private val dbHelper = DatabaseHelper(context)

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
                user.userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_ID))
                user.userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME))
                user.userUsername = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_USERNAME))
                user.userBirthday = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_BIRTHDAY))
                user.userPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD))
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

        return db.update(DatabaseHelper.USER_TABLE, values, "${DatabaseHelper.USER_ID} = ?", arrayOf(user.userId.toString()))
    }

    fun deleteUser(user: User) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.USER_TABLE, "${DatabaseHelper.USER_ID} = ?", arrayOf(user.userId.toString()))
        db.close()
    }

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
                movie.movieId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.MOVIE_ID))
                movie.movieName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MOVIE_NAME))
                movie.movieRating = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.MOVIE_RATING))
                movie.movieReleaseDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MOVIE_RELEASE_DATE))
                movie.movieDuration = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.MOVIE_DURATION))
                movie.movieDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MOVIE_DESCRIPTION))
                movie.movieCast = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MOVIE_CAST))
                movie.movieDirector = cursor.getString(cursor.getColumnIndex(DatabaseHelper.MOVIE_DIRECTOR))
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

        return db.update(DatabaseHelper.MOVIE_TABLE, values, "${DatabaseHelper.MOVIE_ID} = ?", arrayOf(movie.movieId.toString()))
    }

    fun deleteMovie(movie: Movies) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.MOVIE_TABLE, "${DatabaseHelper.MOVIE_ID} = ?", arrayOf(movie.movieId.toString()))
        db.close()
    }
}