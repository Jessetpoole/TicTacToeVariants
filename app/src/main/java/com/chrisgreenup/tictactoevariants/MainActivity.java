package com.chrisgreenup.tictactoevariants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean inMainActivity;

    private ArrayList<Character> [][] board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMain();

    }

    // Sets up the UI to show activity_main.xml
    // Hooks up the button to go to the select activity
    // Hooks up the button which gives info to the user
    void setupMain(){
        Log.i("TTTMenu", "In activity_main now");
        setContentView(R.layout.activity_main);
        inMainActivity = true;

        findViewById(R.id.game_select_button).setOnClickListener(new MenuButton());
        findViewById(R.id.view_rules_button).setOnClickListener(new MenuButton());
    }

    // Sets up the UI to show activity_select.xml
    // Sets the correct content view
    // Hooks up the 3 game mode buttons
    // Hooks up the button to return to the main menu
    void setupSelect(){
        Log.i("TTTMenu", "In activity_select now");
        setContentView(R.layout.activity_select);
        inMainActivity = false;

        findViewById(R.id.game_one_button).setOnClickListener(new SelectButton());
        findViewById(R.id.game_two_button).setOnClickListener(new SelectButton());
        findViewById(R.id.game_three_button).setOnClickListener(new SelectButton());

        findViewById(R.id.return_from_select_button).setOnClickListener(new SelectButton());
    }

    // Sets up the UI to show activity_info.xml
    // Sets the correct content view
    // Hooks up the single button
    void setupInfo(){
        Log.i("TTTMenu", "In activity_info now");
        setContentView(R.layout.activity_info);
        inMainActivity = false;

        // This is the only button in this activity, and all it needs to do is
        // return to the main menu, so onBackPressed is used for simplicity's sake
        findViewById(R.id.return_from_info_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    void setupBoard(){
        setContentView(R.layout.activity_board);

        initializeTheBoardButtons();
        //TODO: implement method of writing board state to a file
        //TODO: implement a method of reading file and applying to board state

        //TODO: implement saving the board (writing) when leaving board in any form
        //TODO: implement board clearing
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
        findViewById(R.id.reset_board_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Put the board clearing thing here
            }
        });
    }

    //Handles all of the onClick actions for the buttons in activity_main.xml
    class MenuButton implements OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.game_select_button){
                setupSelect();
            }
            else{
                setupInfo();
            }
        }
    }

    //Handles all of the onClick actions for the buttons in activity_select.xml
    class SelectButton implements OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.game_one_button){
                //Game one button code here
                Log.i("TTTButton", "Button = 1");
                setupBoard();
            }
            else if(view.getId() == R.id.game_two_button){
                //Game two button code here
                Log.i("TTTButton", "Button = 2");
                setupBoard();
            }
            else if (view.getId() == R.id.game_three_button){
                //Game Three button code here
                Log.i("TTTButton", "Button = 3");
                setupBoard();
            }
            else{
                //Otherwise, the return button has been pressed
                onBackPressed();
            }
        }
    }

    class BoardButton implements  OnClickListener{
        @Override
        public void onClick(View view) {
            //TODO: do the game stuff here
        }
    }

    @Override
    public void onBackPressed() {
        if(!inMainActivity){
            setupMain();
        }
        else {
            super.onBackPressed();
        }
    }
}
