package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameMode4 extends AppCompatActivity{

    private String[][] board;

    private int totalMatches;

    private boolean player1sTurn = true;

    // What mark does the player want to place
    private String mark;

    // Counts the number of rounds up to 9
    private int roundCount;

    //Points for each player
    private int player1Score;
    private int player2Score;

    //Displays the points for each player
    private TextView player1textView;
    private TextView player2textView;
    private TextView playerTurnTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_board);

        initializeTheBoardButtons();
        board = new String[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = "";


        roundCount = 0;
        totalMatches = 0;

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
        mark = "s";

        //This button isn't on the game board, but is put here to reduce clutter
        //Resets the entire game when pressed by called the function resetGame()
        findViewById(R.id.reset_board_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
            }
        });

        findViewById(R.id.reset_game_button).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                resetGame();
                return false;
            }
        });

        findViewById(R.id.mark_ib).setOnClickListener(new View.OnClickListener() {
            ImageButton btn = findViewById(R.id.mark_ib);
            @Override
            public void onClick(View view) {
                if(mark.equals("s")){
                    mark = "o";
                    btn.setImageResource(R.drawable.o);
                }
                else{
                    mark = "s";
                    btn.setImageResource(R.drawable.s);
                }
            }
        });
    }

    //Updates the textViews
    void updateTextViews(){
        player1textView.setText("Player 1 Score: " + player1Score);
        player2textView.setText("Player 2 Score: " + player2Score);
        if(player1sTurn)
            playerTurnTextView.setText("Player 1s Turn.");
        else
            playerTurnTextView.setText("Player 2s Turn.");
        if(roundCount >= 9){
            if(player1Score > player2Score)
                playerTurnTextView.setText("Player 1 wins!");
            else if (player2Score > player1Score)
                playerTurnTextView.setText("Player 2 wins!");
            else
                playerTurnTextView.setText("It's a draw");
        }
    }

    int checkBoard(){
        int total;
        total = checkRows() + checkColumns() + checkDiagonals();

        total -= totalMatches;
        totalMatches += total;
        return total;
    }

    int checkRows(){
        int total = 0;

        for(int y = 0; y < 3; y++){
            if((board[y][0].equals("s")) && (board[y][1].equals("o")) && (board[y][2].equals("s"))){
                total++;
            }
        }

        return total;
    }

    int checkColumns(){
        int total = 0;

        for(int x = 0; x < 3; x++){
            if(board[0][x].equals("s") && board[1][x].equals("o") && board[2][x].equals("s")){
                total++;
            }
        }
        return total;
    }

    int checkDiagonals(){
        int total = 0;

        if(board[0][0].equals("s") && board[1][1].equals("o") && board[2][2].equals("s")){
            total++;
        }

        if(board[2][0].equals("s") && board[1][1].equals("o") && board[0][2].equals("s")){
            total++;
        }

        return total;
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
        mark = "s";
        player1Score = 0;
        player2Score = 0;
        ((ImageButton) findViewById(R.id.mark_ib)).setImageResource(R.drawable.s);

        playerTurnTextView.setText("Player 1s Turn.");
        updateTextViews();

    }

    void resetGame(){
        player1Score = player2Score = 0;
        resetBoard();
    }

    void endGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = "-END-";
            }
        }
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
            if (mark.equals("s")){
                btn.setImageResource(R.drawable.s);
                board[y][x] = "s";
            }
            else{
                btn.setImageResource(R.drawable.o);
                board[y][x] = "o";
            }

            int check = checkBoard();
            if(check > 0){
                if(player1sTurn){
                    player1Score += check;
                }
                else player2Score += check;

            }

            roundCount++;

            if(roundCount >= 9){
                endGame();
            }

            player1sTurn = !player1sTurn;
            updateTextViews();

        }
    }

}