package com.example.stopwatchapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.stopwatchapp.database.TimeEntryDatabase
import com.example.stopwatchapp.models.TimeEntry
import com.example.stopwatchapp.repository.TimeEntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeEntryViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<TimeEntry>>
    private val repository: TimeEntryRepository

    init {
        val timeEntryDao = TimeEntryDatabase.getInstance(application).timeEntryDao()
        repository = TimeEntryRepository(timeEntryDao)
        readAllData = repository.readAllData
    }

    fun addTimeEntry(timeEntry: TimeEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTimeEntry(timeEntry)
        }
    }

    fun deleteTimeEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTimeEntries()
        }
    }
}

class TodoViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(TimeEntryViewModel::class.java)) {
            return TimeEntryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}