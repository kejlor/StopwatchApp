package com.example.stopwatchapp.repository

import androidx.lifecycle.LiveData
import com.example.stopwatchapp.database.TimeEntryDatabaseDao
import com.example.stopwatchapp.models.TimeEntry

class TimeEntryRepository(private val timeEntryDatabaseDao: TimeEntryDatabaseDao) {

    val readAllData: LiveData<List<TimeEntry>> = timeEntryDatabaseDao.getAll()

    suspend fun addTimeEntry(timeEntry: TimeEntry) {
        timeEntryDatabaseDao.insert(timeEntry)
    }

    suspend fun deleteAllTimeEntries() {
        timeEntryDatabaseDao.deleteAll()
    }
}