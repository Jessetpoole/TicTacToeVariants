package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameMode2 extends AppCompatActivity{

    private String[][] board;

    private boolean player1sTurn = true;

    // What mark does the player want to place
    private String mark;

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
        setContentView(R.layout.activity_sos_board);

        initializeTheBoardButtons();
        board = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "";
            }
        }
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
                //resetBoard();
            }
        });

        findViewById(R.id.reset_game_button).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                //resetGame();
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

            Log.i("TTT", "" + x + ", " + y);
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
        }
    }

}