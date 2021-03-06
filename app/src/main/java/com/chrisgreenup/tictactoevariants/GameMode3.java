package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameMode3 extends AppCompatActivity {
    //Array representation of the board
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
    void initializeTheBoardButtons() {
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
                resetBoard();
            }
        });

        findViewById(R.id.reset_game_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    class BoardButton implements View.OnClickListener {
        private int x;
        private int y;


        public BoardButton(int y, int x) {
            this.x = x;
            this.y = y;

        }

        @Override
        public void onClick(View view) {
            ImageButton btn = findViewById(view.getId());

            if(!board[y][x].equals("")){
                return;
            }

            //If it is player 1's turn, set the marking to be X
            if (player1sTurn) {
                board[y][x] = "x";
                btn.setImageResource(R.drawable.cross);
            } else {
                //Otherwise, it must be player 2's turn, set the marking to be O
                board[y][x] = "o";
                btn.setImageResource(R.drawable.circle);
            }

            roundCount++;

            //If somebody just won,
            if (thereIsAWinner()) {
                //and it was player 1's turn, player 1 must have won
                if (player1sTurn) {
                    player1NumWins++;
                    Toast.makeText(getApplicationContext(), "Player 1 Wins!!", Toast.LENGTH_LONG).show();
                } else {//Otherwise, Player 2 must have won
                    player2NumWins++;
                    Toast.makeText(getApplicationContext(), "Player 2 Wins!!", Toast.LENGTH_LONG).show();
                }

                endGame();
                updateTextViews();
            }
            //Otherwise, make sure the game hasn't stalemated
            else if (roundCount == 9) {
                Toast.makeText(getApplicationContext(), "It's a draw.", Toast.LENGTH_LONG).show();
                resetBoard();
            }
            //Otherwise, the game isn't over. FLip a coin to see who's turn it is next;
            else {
                Random random = new Random();
                player1sTurn = random.nextBoolean();

                if (player1sTurn)
                    playerTurnTextView.setText("Player 1s Turn.");
                else
                    playerTurnTextView.setText("Player 2s Turn.");
            }
        }
    }

    //Updates the textViews
    void updateTextViews() {
        player1textView.setText("Player 1: " + player1NumWins);
        player2textView.setText("Player 2: " + player2NumWins);
    }

    void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
        initializeTheBoardButtons();
        roundCount = 0;
        player1sTurn = true;
        playerTurnTextView.setText("Player 1s Turn.");
        updateTextViews();

    }

    void resetGame() {
        player1NumWins = 0;
        player2NumWins = 0;
        resetBoard();
    }

    boolean thereIsAWinner() {
        return (checkColumns() || checkRows() || checkDiagonals());
    }

    boolean checkRows() {
        String lastMark;
        String currentMark;
        int numInARow;

        //First check the columns
        for (int y = 0; y < 3; y++) {
            numInARow = 1;
            lastMark = board[y][0];

            for (int x = 1; x < 3; x++) {
                currentMark = board[y][x];

                if (lastMark.equals(currentMark) && !currentMark.equals("")) {
                    numInARow++;

                    if (numInARow == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean checkColumns() {
        String lastMark;
        String currentMark;
        int numInARow;

        //First check the columns
        for (int x = 0; x < 3; x++) {
            numInARow = 1;
            lastMark = board[0][x];

            for (int y = 1; y < 3; y++) {
                currentMark = board[y][x];

                if (lastMark.equals(currentMark) && !currentMark.equals("")) {
                    numInARow++;

                    if (numInARow == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean checkDiagonals() {
        String lastMark;
        String currentMark;
        int numInARow;


        numInARow = 1;
        lastMark = board[0][0];
        for (int i = 1; i < 3; i++) {
            currentMark = board[i][i];
            if (currentMark.equals(lastMark) && !currentMark.equals("")) {
                numInARow++;
                if (numInARow == 3)
                    return true;
            }
        }

        numInARow = 1;
        lastMark = board[2][0];
        for (int i = 1; i < 3; i++) {
            currentMark = board[2 - i][i];
            if (currentMark.equals(lastMark) && !currentMark.equals("")) {
                numInARow++;
                if (numInARow == 3)
                    return true;
            }

        }

        return false;
    }

    void endGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = "-END-";
            }
        }
    }
}
