package com.example.stopwatchapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stopwatchapp.models.TimeEntry

@Dao
interface TimeEntryDatabaseDao {
    @Query("SELECT * from time_entry_list")
    fun getAll(): LiveData<List<TimeEntry>>

    @Insert
    suspend fun insert(timeEntry: TimeEntry)

    @Query("DELETE from time_entry_list")
    suspend fun deleteAll()
}