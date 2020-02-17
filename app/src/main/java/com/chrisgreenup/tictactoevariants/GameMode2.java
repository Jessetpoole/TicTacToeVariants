package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameMode2 extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCounts = 0;
    private int player1Points = 0;
    private int player2Points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button reset_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode2);

        textViewPlayer1 = (TextView) findViewById(R.id.player1_textView);
        textViewPlayer2 = (TextView) findViewById(R.id.player2_textView);

        reset_button = (Button) findViewById(R.id.button_reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


        buttons[0][0] = (Button) findViewById(R.id.button_00);
        buttons[0][1] = (Button) findViewById(R.id.button_01);
        buttons[0][2] = (Button) findViewById(R.id.button_02);
        buttons[1][0] = (Button) findViewById(R.id.button_10);
        buttons[1][1] = (Button) findViewById(R.id.button_11);
        buttons[1][2] = (Button) findViewById(R.id.button_12);
        buttons[2][0] = (Button) findViewById(R.id.button_20);
        buttons[2][1] = (Button) findViewById(R.id.button_21);
        buttons[2][2] = (Button) findViewById(R.id.button_22);

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


        if (player1Turn) {
            b.setText("N");

        } else {
            b.setText("O");
        }
        roundCounts++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCounts == 9) {
            draw();
        } else {
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


    private boolean checkForWin() {

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


        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])
                    && !board[0][i].equals("")) {

                return true;
            }

        }


        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])
                && !board[0][0].equals("")) {
            return true;
        }

        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])
                && !board[0][2].equals("")) {
            return true;
        }

            return false;

    }


}
