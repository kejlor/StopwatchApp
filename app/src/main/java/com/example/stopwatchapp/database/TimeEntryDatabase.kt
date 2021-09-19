package com.example.stopwatchapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stopwatchapp.models.TimeEntry

@Database(entities = [TimeEntry::class], version = 1, exportSchema = false)
abstract class TimeEntryDatabase : RoomDatabase() {
    abstract fun timeEntryDao(): TimeEntryDatabaseDao

    companion object {
        private var INSTANCE: TimeEntryDatabase? = null

        fun getInstance(context: Context): TimeEntryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TimeEntryDatabase::class.java,
                        "time_entry_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}