package com.richard.tictactoe5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        SharedPreferences pref = this.getSharedPreferences("MyPref",MODE_PRIVATE);
        String winnerName= pref.getString("winner","");
        Button backToGame = (Button)findViewById(R.id.backtogame);
        Button mainMenu = (Button)findViewById(R.id.mainmenu);
        TextView winnerDisplay= (TextView) findViewById(R.id.winnerName);
        winnerDisplay.setText(""+winnerName+" ");
    }
    public void btgClick(View view)
    {
        Intent intent2 = new Intent(Result.this, MainActivity.class);
        startActivity(intent2);
    }

    public void mmClick(View view)
    {
        Intent intent3 = new Intent(Result.this, Details.class);
        startActivity(intent3);
    }

    public void Exit(View view)
    {
        finishAndRemoveTask();
    }

}
