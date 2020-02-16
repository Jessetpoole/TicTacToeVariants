package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.widget.Toast;

public class GameMode1 {
    
    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        //Array for each of the squares
        private Button[][] buttons = new Button[3][3];
        private boolean player1Turn = true;
        // Counts the number of rounds up to 9
        private int roundCount;
        //Points for each player
        private int player1Points;
        private int player2Points;
        //Displays the points for each player
        private TextView player1textView;
        private TextView player2textView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            player1textView = findViewById(R.id.p1_textview);
            player2textView = findViewById(R.id.p2_textview);
            // For Loop that will reference the button array, 3 indicates the number of Rounds
            // With the nested loop we can loop through the 2D array rows and columns with the button ids
            for (int i = 0; i < 3; i++) {
                for (int t = 0; t < 3; t++) {
                    String buttonID = "button_" + i + t;
                    //This is a substitute for R.id since I created the nested loop,
                    int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    //This line I can get references to my buttons without having to doing it one by one
                    buttons[i][t] = findViewById(resID);
                    buttons[i][t].setOnClickListener(this);
                }
            }
            // This sets up the Reset Button through the anonymous inner class
            Button resetButton = findViewById(R.id.reset_button);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetGame();
                }
            });
        }
        @Override
        public void onClick(View v) {
            // on click for playing field buttons; I am casting v into a button
            // This will check to see if the button clicked has a empty string, if not a gamepiece was used
            if (!((Button) v).getText().toString().equals("")) {
                return;
            }
            // This checks if player 1 turn is true and adds texts to the buttons
            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
            // Increments the round
            roundCount++;
            // Winner announced after it is verified by checkForWinner Method (p1,p2,draw)
            if (checkForWinner()) {
                if (player1Turn) {
                    player1Wins();
                } else {
                    player2Wins();
                }
            } else if (roundCount == 9) {
                draw();
            } else {
                // Switch turns
                player1Turn = !player1Turn;
            }
        }
        // Checks for winner
        private boolean checkForWinner() {
            // This will save the text of the buttons in the array
            String[][] board = new String[3][3];
            for (int i = 0; i < 3; i ++) {
                for (int t = 0; t < 3; t++) {
                    board[i][t] = buttons[i][t].getText().toString();
                }
            }
            // These arrays will go through rows an columns and compare the boards next to each other
            // to see if anything has been played, if it is true someone has won the game, if it is
            // false, then we do not have a winner
            // This will check the rows
            for (int i = 0; i <3; i++) {
                if (board[i][0].equals(board[i][1])
                        && board[i][0].equals(board[i][2])
                        && !board[i][0].equals("")) {
                    return true;
                }
            }
            // This will check the columns
            for (int i = 0; i <3; i++) {
                if (board[0][i].equals(board[1][i])
                        && board[0][i].equals(board[2][i])
                        && !board[0][i].equals("")) {
                    return true;
                }
            }
            // This will check diagonal
            if (board[0][0].equals(board[1][1])
                    && board[0][0].equals(board[2][2])
                    && !board[0][0].equals("")) {
                return true;
            }
            // This will also check diagonal
            if (board[0][2].equals(board[1][1])
                    && board[0][2].equals(board[2][0])
                    && !board[0][2].equals("")) {
                return true;
            }
            return false;
        }
        private void player1Wins(){
            player1Points++;
            Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
            updatePoints();
            resetBoard();
        }
        private void player2Wins(){
            player2Points++;
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
            updatePoints();
            resetBoard();
        }
        private void draw(){
            Toast.makeText(this, "DRAW!", Toast.LENGTH_SHORT).show();
            resetBoard();
        }
        public void updatePoints() {
            player1textView.setText("Player 1: " + player1Points);
            player2textView.setText("Player 2: " + player2Points);
        }
        private void resetBoard() {
            for (int i = 0; i < 3; i++) {
                for (int t = 0; t < 3; t++) {
                    buttons[i][t].setText("");
                }
            }
            roundCount = 0;
            player1Turn = true;
        }
        private void resetGame() {
            player1Points = 0;
            player2Points = 0;
            updatePoints();
            resetBoard();
        }
        // This is used save the game information
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("roundCount", roundCount);
            outState.putInt("player1Points", player1Points);
            outState.putInt("player2Points", player2Points);
            outState.putBoolean("player1Turn", player1Turn);
        }
        @Override
        protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            roundCount = savedInstanceState.getInt("roundCount");
            player1Points = savedInstanceState.getInt("player1Points");
            player2Points = savedInstanceState.getInt("player2Points");
            player1Turn = savedInstanceState.getBoolean("player1Turn");
        }
    }
}
