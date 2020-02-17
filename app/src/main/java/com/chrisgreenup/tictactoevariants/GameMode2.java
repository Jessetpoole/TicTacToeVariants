package com.chrisgreenup.tictactoevariants;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameMode2 extends AppCompatActivity implements View.OnClickListener {

    // 2D Array for each of the squares(buttons) used for the board
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    // Counts the number of rounds up to 9
    private int roundCounts = 0;

    //Points for each player
    private int player1Points = 0;
    private int player2Points = 0;

    // Displays the points for each player
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private Random random;

    private Button reset_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode2);

        textViewPlayer1 = (TextView) findViewById(R.id.player1_textView);
        textViewPlayer2 = (TextView) findViewById(R.id.player2_textView);

        reset_button = (Button) findViewById(R.id.button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });


        // Connects buttons on the gameboard
        buttons[0][0] = (Button) findViewById(R.id.button_00);
        buttons[0][1] = (Button) findViewById(R.id.button_01);
        buttons[0][2] = (Button) findViewById(R.id.button_02);
        buttons[1][0] = (Button) findViewById(R.id.button_10);
        buttons[1][1] = (Button) findViewById(R.id.button_11);
        buttons[1][2] = (Button) findViewById(R.id.button_12);
        buttons[2][0] = (Button) findViewById(R.id.button_20);
        buttons[2][1] = (Button) findViewById(R.id.button_21);
        buttons[2][2] = (Button) findViewById(R.id.button_22);

        // References for the buttons
        for (int i = 0; i < 3; i++)
            for (int t = 0; t < 3; t++)
                buttons[i][t].setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Button b = (Button) v;

        if (b.getText().toString().equals("")) {
            return;
        }

        // Checks if player 1 turn is true and adds the text to the buttons
        if (player1Turn) {
            b.setText(""+ random);
            b.setTextColor(Color.parseColor("#000000"));

        } else {
            b.setText("" + random);
            b.setTextColor(Color.parseColor("#000000"));
        }
        // Increments the number of rounds
        roundCounts++;

        // Determinations after it is verified by the checkForWin Method (p1,p2,draw)
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCounts == 9) {
            draw();
        } else {
        // Switch Turns
            player1Turn = !player1Turn;
        }


    }

    private void draw() {
        Toast.makeText(this, "DRAW!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 WINS!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 WINS!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }


    private void updatePoints() {
        textViewPlayer1.setText("Player 1 :" + player1Points);
        textViewPlayer2.setText("Player 2 :" + player2Points);

    }

       // This nested for loop will allow the game to reset to the state that it was first opened
    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int t = 0; t < 3; t++)
                buttons[i][t].setText("");

        roundCounts = 0;
        player1Turn = true;

    }


    private void resetGame() {

        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetBoard();
    }

    // This is the method where it will check for a winner
    private boolean checkForWin() {
    //This will save the text of the buttons in the array
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++)
            for (int t = 0; t < 3; t++)
                board[i][t] = buttons[i][t].getText().toString();


        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])
                    && !board[i][0].equals("")) {

                return true;
            }

        }
    // These arrays will go through rows and columns and compare the boards next to each other to
    // to see if anything has been played, if it is true someone has won the game, if it is false
    // then we do not yet have a winner.

    // This will check the rows (horizontal)
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])
                    && !board[0][i].equals("")) {
                return true;
            }
        }
    // This will check the columns (vertical)
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])
                && !board[0][0].equals("")) {
            return true;
        }
    // This will check the diagonals
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])
                && !board[0][2].equals("")) {
            return true;
        }

            return false;



    }

    // This is used to save the game information
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCounts", roundCounts);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCounts = savedInstanceState.getInt("roundCounts");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }
}
