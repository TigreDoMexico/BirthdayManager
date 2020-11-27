package com.david.birthdaymanager.repository

import androidx.annotation.WorkerThread
import com.david.birthdaymanager.dao.BirthdayDao
import com.david.birthdaymanager.data.Birthday
import kotlinx.coroutines.flow.Flow

class BirthdayRepository(private val birthdayDao: BirthdayDao){
    val allBirthdays: Flow<List<Birthday>> = birthdayDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(birthday: Birthday){
        birthdayDao.insert(birthday)
    }

}