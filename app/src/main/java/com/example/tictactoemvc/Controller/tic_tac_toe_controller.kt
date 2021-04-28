package com.example.tictactoemvc.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.example.tictactoemvc.Model.Database
import com.example.tictactoemvc.R
import com.example.tictactoemvc.Model.TicTacToeGame
import kotlinx.android.synthetic.main.tic_tac_toe_view.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // textview for current gamestate
    private lateinit var gameStateTextView: TextView
    // 2D-button-array
    private var buttons: kotlin.Array<kotlin.Array<Button?>> =
            Array(3) { arrayOfNulls<Button>(3) }
    // tic-tac-toe-game
    private lateinit var game: TicTacToeGame
    // newGame and resetScore button
    private lateinit var newGame: Button
    private lateinit var resetScore: Button
    // database-variable
    companion object {
        lateinit var db: Database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // initialize DB
        db = Room.databaseBuilder(applicationContext, Database::class.java, "dataBaseEntry")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        // insert player X and O, if not already existing
        tryInsertPlayer("X", 0)
        tryInsertPlayer("O", 1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tic_tac_toe_view)

        // textview for current Gamestate
        gameStateTextView = textView

        // put Buttons in 2D-Array
        buttons[0][0] = button00
        buttons[0][1] = button01
        buttons[0][2] = button02
        buttons[1][0] = button10
        buttons[1][1] = button11
        buttons[1][2] = button12
        buttons[2][0] = button20
        buttons[2][1] = button21
        buttons[2][2] = button22

        // initialize the Tic-Tac-Toe-Game
        game = TicTacToeGame(this)

        // add Listeners to the Tic-Tac-Toe-Buttons
        for (row in 0..2) {
            for (column in 0..2) {
                buttons[row][column]?.setOnClickListener(this)
            }
        }

        // initialize newGame and resetScore Button
        // add Listeners
        newGame = newGameButton
        newGame.setOnClickListener(this)
        resetScore = resetScoreButton
        resetScore.setOnClickListener(this)

        // write score to the Textviews
        scoreO.text = game.scoreO.toString()
        scoreX.text = game.scoreX.toString()

    }

    // insert player into database
    private fun tryInsertPlayer(player: String, id: Int) {
        try {
            // if player not already in database
            if (db.dataBaseQueries().getName(id) != player) {
                // insert it
                db.dataBaseQueries().insertPlayer(player, 0)
                return
            }
        } catch (e: Exception) {
            // insert it
            db.dataBaseQueries().insertPlayer(player, 0)
            return
        }
        return
    }

    // if button clicked
    override fun onClick(v: View?) {
        if (v != null) {
            // if resetGame button clicked
            if (v.id == newGameButton.id) {
                // reset game (field)
                game.resetGame()
            }
            // if resetScore button clicked
            if (v.id == resetScore.id) {
                // reset score
                game.resetScore()
            }
            // if one of the tic-tac-toe-buttons clicked
            for (row in 0..2) {
                for (column in 0..2) {
                    if (v.id == buttons[row][column]?.id) {
                        // check who clicked the button
                        game.clickedButton(row, column)
                    }
                    // set corresponding button text
                    buttons[row][column]?.setText(game.stringForButtonAtLocation(row, column))

                    // update score
                    scoreO.text = game.scoreO.toString()
                    scoreX.text = game.scoreX.toString()
                }
            }
        }

        // update gamestate
        gameStateTextView.setText(game.stringForGameState())
    }
}