package com.example.tictactoemvc.Model

import androidx.room.*

// database queries
@Dao
interface DataBaseQueries {
    // insert player into database
    @Query("INSERT INTO DataBaseEntry(name, score) values(:n,:p)")
    fun insertPlayer(n: String, p: Int)

    // update score of a player
    @Query("UPDATE DataBaseEntry SET score = :p where name == :n")
    fun updateScore(n: String, p: Int)

    // get score of a player
    @Query("SELECT score FROM DataBaseEntry WHERE name == :n")
    fun getScore(n: String): Int

    // get name of a player
    @Query("SELECT name from DataBaseEntry where id == :i")
    fun getName(i: Int): String

    // reset score of a player
    @Query("UPDATE DataBaseEntry SET score = 0 where name == :n")
    fun resetScore(n: String)
}