package com.example.tictactoemvc.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Database-Entry
// id, name (X / O), score
@Entity
data class DataBaseEntry(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        @ColumnInfo(name = "name")
        val name: String ,
        val score: Int
)