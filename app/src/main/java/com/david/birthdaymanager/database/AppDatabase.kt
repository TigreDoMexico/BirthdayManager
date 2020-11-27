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
                    ).addCallback(BirthdayDatabaseCallback(scope)).build()

                INSTANCE = instance

                instance
            }

        }
    }

    private class BirthdayDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {database ->
                scope.launch {
                    populateDatabase(database.birthdayDao())
                }
            }
        }

        suspend fun populateDatabase(birthdayDao: BirthdayDao) {
            birthdayDao.deleteAll()

            var birthday = Birthday(name = "Aniversário 1", date = "11/10");
            birthdayDao.insert(birthday)
            birthday = Birthday(name = "Aniversário 2", date = "11/10");
            birthdayDao.insert(birthday)
        }

    }

}
