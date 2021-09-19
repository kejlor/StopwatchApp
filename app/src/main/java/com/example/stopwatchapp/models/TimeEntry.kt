package com.example.stopwatchapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_entry_list")
data class TimeEntry(
    @ColumnInfo(name = "time_elapsed")
    val timeElapsed: Long,
    @PrimaryKey(autoGenerate = true)
    var timeId: Long = 0L
) {
    constructor(timeElapsed: Long) : this (timeElapsed, 0L)
}