package com.chrisgreenup.tictactoevariants;
//test

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private boolean inMainActivity;

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
        Intent intent = new Intent(getApplicationContext(), GameMode1.class);
        startActivity(intent);
        //setContentView(R.layout.activity_board); !REMOVE LATER

        //TODO: implement method of writing board state to a file
        //TODO: implement a method of reading file and applying to board state

        //TODO: implement saving the board (writing) when leaving board in any form
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
                Intent intent = new Intent(getApplicationContext(), GameMode2.class);
                startActivity(intent);
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
