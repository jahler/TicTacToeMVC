package com.example.tictactoemvc.Model

import android.content.Context
import com.example.tictactoemvc.Controller.MainActivity
import com.example.tictactoemvc.R

class TicTacToeGame(private val context: Context) {

    // different game states
    private enum class GameState {
        X_TURN, O_TURN, X_WIN, O_WIN, TIE_GAME
    }

    // get scores from database at beginning
    var scoreX: Int = MainActivity.db.dataBaseQueries().getScore("X")
    var scoreO: Int = MainActivity.db.dataBaseQueries().getScore("O")

    private lateinit var lastWinner: String

    private var gameState: GameState? = null
    // reset game
    private lateinit var boardArray: Array<IntArray>
    fun resetGame() {
        // reset boardArray
        boardArray = Array(
                NUM_ROWS
        ) { IntArray(NUM_COLUMNS) }
        // who last lost begins
        if (lastWinner == "X") {
            gameState = GameState.X_TURN
        } else if (lastWinner == "O") {
            gameState = GameState.O_TURN
        }
    }

    // check which button was clicked
    fun clickedButton(row: Int, column: Int) {
        // Not a valid button location
        if (row < 0 || row >= NUM_ROWS || column < 0 || column >= NUM_COLUMNS) return
        // Not an empty button
        if (boardArray[row][column] != MARK_NONE) return
        // if it was X's turn
        if (gameState == GameState.X_TURN) {
            // set the button to X
            boardArray[row][column] = MARK_X
            // it's O's turn
            gameState = GameState.O_TURN
        // if it was O's turn
        } else if (gameState == GameState.O_TURN) {
            // set the button to O
            boardArray[row][column] = MARK_O
            // it's X's turn
            gameState = GameState.X_TURN
        }
        // check for win after player has clicked
        check()
    }

    // check for win
    private fun check() {
        // if another gamestate than someone's turn
        if (!(gameState == GameState.X_TURN || gameState == GameState.O_TURN)) return
        // if X wins
        if (didPieceWin(MARK_X)) {
            // set gamestate to 'X wins'
            gameState = GameState.X_WIN
            // save X a point in the database
            var score = MainActivity.db.dataBaseQueries().getScore("X")
            score++
            MainActivity.db.dataBaseQueries().updateScore("X", score)
            //println(MainActivity.db.dataBaseQueries().getScore("X"))
            scoreX=score
            // set last winner to X
            lastWinner = "X"
        // if O wins
        } else if (didPieceWin(MARK_O)) {
            // set gamestate to 'O wins'
            gameState = GameState.O_WIN
            // save O a point in database
            var score = MainActivity.db.dataBaseQueries().getScore("O")
            score++
            MainActivity.db.dataBaseQueries().updateScore("O", score)
            //println(MainActivity.db.dataBaseQueries().getScore("O"))
            scoreO=score
            // set last winner to O
            lastWinner = "O"
        // if board is full
        } else if (isBoardFull) {
            // set gamestate to 'tie'
            gameState = GameState.TIE_GAME
        }
    }

    // check if the board is full
    private val isBoardFull: Boolean
        get() {
            for (row in 0 until NUM_ROWS) {
                for (col in 0 until NUM_COLUMNS) {
                    // if a button was clicked
                    if (boardArray[row][col] == MARK_NONE) {
                        return false
                    }
                }
            }
            return true
        }

    // check all possible wins
    private fun didPieceWin(markType: Int): Boolean {
        var allMarksMatch: Boolean
        // Check all the columns for a win
        for (col in 0 until NUM_COLUMNS) {
            allMarksMatch = true
            for (row in 0 until NUM_ROWS) {
                if (boardArray[row][col] != markType) {
                    allMarksMatch = false
                    break
                }
            }
            if (allMarksMatch) return true
        }

        // Check all the rows for a win
        for (row in 0 until NUM_ROWS) {
            allMarksMatch = true
            for (col in 0 until NUM_COLUMNS) {
                if (boardArray[row][col] != markType) {
                    allMarksMatch = false
                    break
                }
            }
            if (allMarksMatch) return true
        }

        // Check up left down right diagonal
        if (boardArray[0][0] == markType && boardArray[1][1] == markType && boardArray[2][2] == markType
        ) return true

        // Check down left up right diagonal
        return boardArray[2][0] == markType && boardArray[1][1] == markType && boardArray[0][2] == markType
    }

    // check button states and return their text
    fun stringForButtonAtLocation(row: Int, column: Int): String {
        val label = ""
        if (row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_COLUMNS) {
            if (boardArray[row][column] == MARK_X) {
                return "X"
            } else if (boardArray[row][column] == MARK_O) {
                return "O"
            }
        }
        return label
    }

    // check gamestate and return the text
    fun stringForGameState(): String {
        var gameStateLabel = ""
        val r = context.resources
        gameStateLabel = when (gameState) {
            GameState.X_TURN -> r.getString(R.string.x_turn)
            GameState.O_TURN -> r.getString(R.string.o_turn)
            GameState.X_WIN -> r.getString(R.string.x_win)
            GameState.O_WIN -> r.getString(R.string.o_win)
            else -> r.getString(R.string.tie_game)
        }
        return gameStateLabel
    }

    // reset score in database
    fun resetScore() {
        MainActivity.db.dataBaseQueries().resetScore("X")
        MainActivity.db.dataBaseQueries().resetScore("O")

        scoreX = MainActivity.db.dataBaseQueries().getScore("X")
        scoreO = MainActivity.db.dataBaseQueries().getScore("O")
    }

    companion object {
        const val NUM_ROWS = 3
        const val NUM_COLUMNS = 3
        private const val MARK_NONE = 0
        private const val MARK_X = 1
        private const val MARK_O = 2
    }

    init {
        resetGame()
    }
}