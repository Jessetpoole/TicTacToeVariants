package com.chrisgreenup.tictactoevariants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameModes extends AppCompatActivity {

    //ArrayList representation of the board
    private String[][] board;

    private boolean player1sTurn = true;

    // Counts the number of rounds up to 9
    private int roundCount;

    //Points for each player
    private int player1NumWins;
    private int player2NumWins;

    //Displays the points for each player
    private TextView player1textView;
    private TextView player2textView;
    private TextView playerTurnTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        board = new String[3][3];
        initializeTheBoardButtons();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }

        player1textView = findViewById(R.id.player_one_tv);
        player2textView = findViewById(R.id.player_two_tv);
        playerTurnTextView = findViewById(R.id.player_turn_tv);
        playerTurnTextView.setText("Player 1s Turn.");
    }

    //  Hooks up all of the buttons on activity_board.xml
    //  This is its own method to improve readability when setting up the board because this
    //  is just a mess, and makes the other method hard to read
    void initializeTheBoardButtons(){
        ImageButton btn_0_0 = (ImageButton) findViewById(R.id.space_0_0);
        btn_0_0.setOnClickListener(new BoardButton(0, 0));
        btn_0_0.setImageResource(R.drawable.empty);
        ImageButton btn_0_1 = (ImageButton) findViewById(R.id.space_0_1);
        btn_0_1.setOnClickListener(new BoardButton(0, 1));
        btn_0_1.setImageResource(R.drawable.empty);
        ImageButton btn_0_2 = (ImageButton) findViewById(R.id.space_0_2);
        btn_0_2.setOnClickListener(new BoardButton(0, 2));
        btn_0_2.setImageResource(R.drawable.empty);
        ImageButton btn_1_0 = (ImageButton) findViewById(R.id.space_1_0);
        btn_1_0.setOnClickListener(new BoardButton(1, 0));
        btn_1_0.setImageResource(R.drawable.empty);
        ImageButton btn_1_1 = (ImageButton) findViewById(R.id.space_1_1);
        btn_1_1.setOnClickListener(new BoardButton(1, 1));
        btn_1_1.setImageResource(R.drawable.empty);
        ImageButton btn_1_2 = (ImageButton) findViewById(R.id.space_1_2);
        btn_1_2.setOnClickListener(new BoardButton(1, 2));
        btn_1_2.setImageResource(R.drawable.empty);
        ImageButton btn_2_0 = (ImageButton) findViewById(R.id.space_2_0);
        btn_2_0.setOnClickListener(new BoardButton(2, 0));
        btn_2_0.setImageResource(R.drawable.empty);
        ImageButton btn_2_1 = (ImageButton) findViewById(R.id.space_2_1);
        btn_2_1.setOnClickListener(new BoardButton(2, 1));
        btn_2_1.setImageResource(R.drawable.empty);
        ImageButton btn_2_2 = (ImageButton) findViewById(R.id.space_2_2);
        btn_2_2.setOnClickListener(new BoardButton(2, 2));
        btn_2_2.setImageResource(R.drawable.empty);

        //This button isn't on the game board, but is put here to reduce clutter
        //Resets the entire game when pressed by called the function resetGame()
        findViewById(R.id.reset_board_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    class BoardButton implements View.OnClickListener {
        private int x;
        private int y;
        private String gameMark = "EMPTY";


        public BoardButton(int y, int x){
            this.x = x;
            this.y = y;

        }

        @Override
        public void onClick(View view) {
            ImageButton btn = findViewById(view.getId());

            if(!gameMark.equals("EMPTY")){
                return;
            }

            //If it is player 1's turn, set the marking to be X
            if(player1sTurn){
                gameMark = "x";
                board[y][x] = "x";
                btn.setImageResource(R.drawable.cross);
            } else{
                //Otherwise, it must be player 2's turn, set the marking to be O
                gameMark = "o";
                board[y][x] = "o";
                btn.setImageResource(R.drawable.circle);
            }

            roundCount++;

            //If somebody just won,
            if(thereIsAWinner()){
                //and it was player 1's turn, player 1 must have won
                if(player1sTurn){
                    player1NumWins++;
                    Toast.makeText(getApplicationContext(), "Player 1 Wins!!", Toast.LENGTH_LONG).show();
                } else {//Otherwise, Player 2 must have won
                    player2NumWins++;
                    Toast.makeText(getApplicationContext(),"Player 2 Wins!!", Toast.LENGTH_LONG).show();
                }

                resetBoard();
                updateTextViews();
            }
            //Otherwise, make sure the game hasn't stalemated
            else if(roundCount == 9){
                Toast.makeText(getApplicationContext(), "It's a draw.", Toast.LENGTH_LONG).show();
                resetBoard();
            }
            //Otherwise, the game isn't over. Switch turns to the next player
            else{
                player1sTurn = !player1sTurn;
                if(player1sTurn)
                    playerTurnTextView.setText("Player 1s Turn.");
                else
                    playerTurnTextView.setText("Player 2s Turn.");
            }
        }
    }

    //Updates the textViews
    void updateTextViews(){
        player1textView.setText("Player 1: " + player1NumWins);
        player2textView.setText("Player 2: " + player2NumWins);
    }

    void resetBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }
        initializeTheBoardButtons();
        roundCount = 0;
        player1sTurn = true;
        playerTurnTextView.setText("Player 1s Turn.");
        updateTextViews();

    }
    void resetGame(){
        player1NumWins = 0;
        player2NumWins = 0;
        resetBoard();
    }

    boolean thereIsAWinner(){
        return (checkColumns() || checkRows());
    }

    boolean checkRows(){
        String lastMark;
        String currentMark;
        int numInARow;

        //First check the columns
        for(int y = 0; y < 3; y++) {
            numInARow = 1;
            lastMark = board[y][0];

            for (int x = 1; x < 3; x++) {
                currentMark = board[y][x];

                if (lastMark.equals(currentMark) && !currentMark.equals("")) {
                    numInARow++;

                    if (numInARow == 3) {
                        return true;
                    }
                } else {
                    x += 2;
                }
            }
        }

        return false;
    }

    boolean checkColumns(){
        String lastMark;
        String currentMark;
        int numInARow;

        //First check the columns
        for(int x = 0; x < 3; x++) {
            numInARow = 1;
            lastMark = board[0][x];

            for (int y = 1; y < 3; y++) {
                currentMark = board[y][x];

                if (lastMark.equals(currentMark) && !currentMark.equals("")) {
                    numInARow++;

                    if (numInARow == 3) {
                        return true;
                    }
                } else {
                    y += 2;
                }
            }
        }

        return false;
    }

    // This is used save the game information
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1NumWins);
        outState.putInt("player2Points", player2NumWins);
        outState.putBoolean("player1Turn", player1sTurn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1NumWins = savedInstanceState.getInt("player1Points");
        player2NumWins = savedInstanceState.getInt("player2Points");
        player1sTurn = savedInstanceState.getBoolean("player1Turn");
    }
}
