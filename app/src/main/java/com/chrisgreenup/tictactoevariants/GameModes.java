package com.chrisgreenup.tictactoevariants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameModes extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initializeTheBoardButtons();
    }

    //  Hooks up all of the buttons on activity_board.xml
    //  This is its own method to improve readability when setting up the board because this
    //  is just a mess, and makes the other method hard to read
    void initializeTheBoardButtons(){
        findViewById(R.id.space_0_0).setOnClickListener(new BoardButton());
        findViewById(R.id.space_0_1).setOnClickListener(new BoardButton());
        findViewById(R.id.space_0_2).setOnClickListener(new BoardButton());
        findViewById(R.id.space_1_0).setOnClickListener(new BoardButton());
        findViewById(R.id.space_1_1).setOnClickListener(new BoardButton());
        findViewById(R.id.space_1_2).setOnClickListener(new BoardButton());
        findViewById(R.id.space_2_0).setOnClickListener(new BoardButton());
        findViewById(R.id.space_2_1).setOnClickListener(new BoardButton());
        findViewById(R.id.space_2_2).setOnClickListener(new BoardButton());
        findViewById(R.id.reset_board_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Put the board clearing thing here
            }
        });
    }

    class BoardButton implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: do the game stuff here
            Log.i("Button", "clicked");
        }
    }
}
