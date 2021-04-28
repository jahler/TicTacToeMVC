package com.example.tictactoemvc.Model

import androidx.room.Database
import androidx.room.RoomDatabase

// Room database
@Database(entities = [DataBaseEntry::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun dataBaseQueries(): DataBaseQueries
}