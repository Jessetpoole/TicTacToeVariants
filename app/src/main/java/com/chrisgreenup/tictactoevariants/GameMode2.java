package com.chrisgreenup.tictactoevariants;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

      textViewPlayer1=(TextView)findViewById(R.id.player1_textView);
      textViewPlayer2=(TextView)findViewById(R.id.player2_textView);

      reset_button=(Button)findViewById(R.id.button_reset);

      buttons[0][0]=(Button)findViewById(R.id.button_00);
      buttons[0][1]=(Button)findViewById(R.id.button_01);
      buttons[0][2]=(Button)findViewById(R.id.button_02);
      buttons[1][0]=(Button)findViewById(R.id.button_10);
      buttons[1][1]=(Button)findViewById(R.id.button_11);
      buttons[1][2]=(Button)findViewById(R.id.button_12);
      buttons[2][0]=(Button)findViewById(R.id.button_20);
      buttons[2][1]=(Button)findViewById(R.id.button_21);
      buttons[2][2]=(Button)findViewById(R.id.button_22);

      for(int i =0; i<3; i++)
          for (int t=0; t<3; t++)
              buttons[i][t].setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        Button b = (Button) v;

        if (b.getText().toString().equals("")) {
            return;
        }


        if (player1Turn) {
            b.setText("X");

        } else {
            b.setText("O");
        }
        roundCounts++;

    }
}
