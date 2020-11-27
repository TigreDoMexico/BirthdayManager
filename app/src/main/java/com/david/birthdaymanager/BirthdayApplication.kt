package com.david.birthdaymanager

import android.app.Application
import com.david.birthdaymanager.database.AppDatabase
import com.david.birthdaymanager.repository.BirthdayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BirthdayApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BirthdayRepository(database.birthdayDao())}
}