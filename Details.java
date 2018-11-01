package com.richard.tictactoe5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }

    public void letsGoButtonClick(View view)
    {
        Button letsGoButton = (Button)view;
        String player1Name, player2Name;
        EditText player1, player2;
        player1=(EditText)findViewById(R.id.player1Name);
        player2=(EditText)findViewById(R.id.player2Name);
        player1Name=player1.getText().toString();
        player2Name=player2.getText().toString();
        Toast.makeText(Details.this, player1Name, Toast.LENGTH_SHORT).show();
        Toast.makeText(Details.this, player2Name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Details.this,MainActivity.class);

        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("pl1", player1Name);  // Saving string
        editor.putString("pl2", player2Name);
        // Save the changes in SharedPreferences
        editor.commit(); // commit changes
        startActivity(new Intent(Details.this,MainActivity.class));
    }
}
