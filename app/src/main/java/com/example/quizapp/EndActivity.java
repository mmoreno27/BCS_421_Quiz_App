package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    private Button menu;
    private TextView finalScoreText;
    private TextView finalScoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        finalScoreText = findViewById(R.id.finalScoreView);
        finalScoreDisplay = findViewById(R.id.finalScoreDisplay);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();

        int scoreInt = pref.getInt("userScore",0);
        finalScoreDisplay.setText(String.valueOf(scoreInt));

        menu = findViewById(R.id.main_menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(EndActivity.this, MenuActivity.class);
                EndActivity.this.startActivity(activityChangeIntent);
            }
        });
    }
}
