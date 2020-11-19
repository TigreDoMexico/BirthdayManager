package com.david.birthdaymanager.dao

import androidx.room.*
import com.david.birthdaymanager.data.Birthday
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface BirthdayDao {
    @Query("SELECT * FROM birthday")
    fun getAll(): Flow<List<Birthday>>

    @Query("SELECT * FROM birthday WHERE id IN (:birthdayIds)")
    fun loadAllByIds(birthdayIds: IntArray): Flow<List<Birthday>>

    @Query("SELECT * FROM birthday WHERE name LIKE :term")
    fun findByName(term: String): Birthday

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(birthday: Birthday)

    @Delete
    fun delete(birthday: Birthday)
}