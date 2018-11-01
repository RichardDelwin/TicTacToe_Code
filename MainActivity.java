package com.richard.tictactoe5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Details {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonClick(View view)
    {

        Intent intent = new Intent(MainActivity.this,Result.class);
        //String player1str=getIntent().getStringExtra("pl1");
        //String player2str=getIntent().getStringExtra("pl2");
       /* SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String player1str = sharedPref.getString("pl1", "NotAvailable");
        String player2str = sharedPref.getString("pl2","");*/
        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        String player1str=pref.getString("pl1", "");
        String player2str = pref.getString("pl2", "");
        Button selectButton =(Button) view;
        int bId = 0;
        TextView displayPlayer;
        displayPlayer = (TextView)findViewById(R.id.textView3);
        displayPlayer.setText((CharSequence)(" "+player1str+" "));
        switch (selectButton.getId())
        {
            case R.id.button1:
                bId = 1;
                break;
            case R.id.button2:
                bId = 2;
                break;
            case R.id.button3:
                bId = 3;
                break;
            case R.id.button4:
                bId = 4;
                break;
            case R.id.button5:
                bId = 5;
                break;
            case R.id.button6:
                bId = 6;
                break;
            case R.id.button7:
                bId = 7;
                break;
            case R.id.button8:
                bId = 8;
                break;
            case R.id.button9:
                bId = 9;
                break;
        }
        this.play(bId, selectButton,intent,displayPlayer, player1str,player2str,editor);
    }

    int k = 0;
    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();

    int activePlayer = 1;
    int turn = 0;

    private void play(int bId, Button selectButton,Intent intent, TextView displayPlayer,String player1str,String player2str, SharedPreferences.Editor editor) {
        if (activePlayer == 1) {
            if (k == 1) {
                selectButton.setEnabled(false);
            } else {
                selectButton.setText((CharSequence)"X");
                selectButton.setBackgroundColor(Color.parseColor("#CC0000"));
                player1.add(bId);
                turn++;
                displayPlayer.setText((" "+player1str+" "));
                activePlayer = 2;
            }
        } else {
            if (k == 1)
                selectButton.setEnabled(false);
            else {
                selectButton.setText((CharSequence)"O");
                displayPlayer.setText((CharSequence)(""+player2str+""));
                selectButton.setBackgroundColor(Color.parseColor("#177245"));
                player2.add(bId);
                turn++;
                activePlayer = 1;
            }
        }
        selectButton.setEnabled(false);
        check(intent, editor, player1str,player2str);
    }

    private void check(Intent intent, SharedPreferences.Editor editor,String player1str,String player2str) {

        int winner = 0;
        if (turn > 4) {
            if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
                winner = 1;
            }
            if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
                winner = 2;
            }

            //Row2
            if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
                winner = 1;
            }
            if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
                winner = 2;
            }

            //row2
            if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
                winner = 1;
            }
            if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
                winner = 2;
            }

            //col1
            if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
                winner = 1;
            }
            if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
                winner = 2;
            }
            //col2
            if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
                winner = 1;
            }
            if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
                winner = 2;
            }
            //col3
            if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
                winner = 1;
            }
            if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
                winner = 2;
            }
            //diag1
            if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
                winner = 1;
            }
            if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
                winner = 2;
            }
            //diag2
            if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
                winner = 1;
            }
            if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
                winner = 2;
            }


            if (winner != 0) {
                k = 1;
                if(winner==1) {
                    Toast.makeText(MainActivity.this, "" + player1str + " Wins!!!", Toast.LENGTH_SHORT).show();
                    editor.putString("winner", player1str+" Wins!!!");
                    editor.commit();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "" + player2str + " Wins!!!", Toast.LENGTH_SHORT).show();
                    editor.putString("winner", player2str+" Wins!!!");
                    editor.commit();
                    startActivity(intent);
                }
               /* final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }
                        , 2000);*/
            }
        }

       if(turn==9)
        {
            Toast.makeText(MainActivity.this, "It's a DRAW!!!",Toast.LENGTH_SHORT).show();
            k=1;
            editor.putString("winner", "It's a Draw!!!");
            editor.commit();
            startActivity(intent);
            /*final Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },2000);*/
        }
    }
}