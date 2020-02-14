package com.chrisgreenup.tictactoevariants;

import androidx.appcompat.app.AppCompatActivity;

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

    void setupMain(){
        Log.i("TTTMenu", "In activity_main now");
        setContentView(R.layout.activity_main);
        inMainActivity = true;

        findViewById(R.id.game_select_button).setOnClickListener(new MenuButton());
        findViewById(R.id.view_rules_button).setOnClickListener(new MenuButton());
    }

    void setupSelect(){
        Log.i("TTTMenu", "In activity_select now");
        setContentView(R.layout.activity_select);
        inMainActivity = false;

        findViewById(R.id.game_one_button).setOnClickListener(new SelectButton());
        findViewById(R.id.game_two_button).setOnClickListener(new SelectButton());
        findViewById(R.id.game_three_button).setOnClickListener(new SelectButton());

        findViewById(R.id.back_button).setOnClickListener(new SelectButton());
    }

    class MenuButton implements OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.game_select_button){
                setupSelect();
            }
            else{
                //Put stuff here for Info button
            }
        }
    }

    class SelectButton implements OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.game_one_button){
                //Game one button code here
                Log.i("TTTButton", "Button = 1");
            }
            else if(view.getId() == R.id.game_two_button){
                //Game two button code here
                Log.i("TTTButton", "Button = 2");
            }
            else if (view.getId() == R.id.game_three_button){
                //Game Three button code here
                Log.i("TTTButton", "Button = 3");
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
