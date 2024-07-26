package com.example.mobdeve_group6_machineproject
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ProjectDatabase"

        // User table constants
        const val USER_TABLE = "users"
        const val USER_ID = "userId"
        const val USER_NAME = "userName"
        const val USER_USERNAME = "userUsername"
        const val USER_BIRTHDAY = "userBirthday"
        const val USER_PASSWORD = "userPassword"

        // Movie table constants
        const val MOVIE_TABLE = "movies"
        const val MOVIE_ID = "movieId"
        const val MOVIE_NAME = "movieName"
        const val MOVIE_RATING = "movieRating"
        const val MOVIE_RELEASE_DATE = "movieReleaseDate"
        const val MOVIE_DURATION = "movieDuration"
        const val MOVIE_DESCRIPTION = "movieDescription"
        const val MOVIE_CAST = "movieCast"
        const val MOVIE_DIRECTOR = "movieDirector"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USER_TABLE = """
            CREATE TABLE $USER_TABLE(
            $USER_ID INTEGER PRIMARY KEY,
            $USER_NAME TEXT,
            $USER_USERNAME TEXT,
            $USER_BIRTHDAY TEXT, /*is birthday text??*/
            $USER_PASSWORD TEXT
            )
        """

        val CREATE_MOVIE_TABLE = """
            CREATE TABLE $MOVIE_TABLE (
            $MOVIE_ID INTEGER PRIMARY KEY,
            $MOVIE_NAME TEXT,
            $MOVIE_RATING REAL,
            $MOVIE_RELEASE_DATE TEXT,
            $MOVIE_DURATION INTEGER,
            $MOVIE_DESCRIPTION TEXT,
            $MOVIE_CAST TEXT,
            $MOVIE_DIRECTOR TEXT
            )
        """
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_MOVIE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $MOVIE_TABLE")
        onCreate(db)
    }
}
