package com.example.mobdeve_group6_machineproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

        // Review table
        const val REVIEW_TABLE = "reviews"
        const val REVIEW_ID = "reviewId"
        const val REVIEW_TITLE = "reviewTitle"
        const val REVIEW_RATING = "reviewRating"
        const val REVIEW_INPUT = "reviewInput"
        const val REVIEW_USER_ID = "userId"
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

        val CREATE_REVIEW_TABLE = """
            CREATE TABLE $REVIEW_TABLE (
                $REVIEW_ID INTEGER PRIMARY KEY,
                $REVIEW_TITLE TEXT,
                $REVIEW_RATING REAL,
                $REVIEW_INPUT TEXT,
                $REVIEW_USER_ID INTEGER,
                FOREIGN KEY($REVIEW_USER_ID) REFERENCES $USER_TABLE($USER_ID)
            )
        """

        db.execSQL(CREATE_USER_TABLE)
        db.execSQL(CREATE_MOVIE_TABLE)
        db.execSQL(CREATE_REVIEW_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $MOVIE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $REVIEW_TABLE")

        onCreate(db)
    }

    // Method to add a new user
    fun addUser(name: String, username: String, birthday: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_NAME, name)
        contentValues.put(USER_USERNAME, username)
        contentValues.put(USER_BIRTHDAY, birthday)
        contentValues.put(USER_PASSWORD, password)

        return db.insert(USER_TABLE, null, contentValues)
    }

    // Method to get a user based on username and password
    fun getUser(username: String, password: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $USER_TABLE WHERE $USER_USERNAME = ? AND $USER_PASSWORD = ?", arrayOf(username, password))
    }
}
