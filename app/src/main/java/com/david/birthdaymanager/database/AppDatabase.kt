package com.david.birthdaymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.david.birthdaymanager.dao.BirthdayDao
import com.david.birthdaymanager.data.Birthday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Birthday::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun birthdayDao(): BirthdayDao
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "birthday_database"
                    ).build()

                INSTANCE = instance

                return instance
            }

        }
    }
}
