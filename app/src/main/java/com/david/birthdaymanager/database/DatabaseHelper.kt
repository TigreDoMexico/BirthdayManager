package com.david.birthdaymanager.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.david.birthdaymanager.data.Birthday

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    override fun onCreate(db: SQLiteDatabase) {
        var create_table = "CREATE TABLE " + BIRTHDAY_TABLE_NAME +
                            " (" +
                                KEY_BIRTHDAY_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                KEY_BIRTHDAY_NAME + " TEXT" +
                                KEY_BIRTHDAY_DATE + " TEXT" +
                            ")"

        db.execSQL(create_table);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + BIRTHDAY_TABLE_NAME);
            onCreate(db)
        }
    }

    fun addData(birthday: Birthday): Boolean{
        //TODO
        return true
    }

    companion object {
        // Database Info
        private const val DATABASE_NAME = "BirthdayDB.db"
        private const val DATABASE_VERSION = 1

        // Table Name
        private const val BIRTHDAY_TABLE_NAME = "Birthday"

        // User Table Columns
        private const val KEY_BIRTHDAY_ID = "id"
        private const val KEY_BIRTHDAY_NAME = "birthdayName"
        private const val KEY_BIRTHDAY_DATE = "birthdayName"
    }
}