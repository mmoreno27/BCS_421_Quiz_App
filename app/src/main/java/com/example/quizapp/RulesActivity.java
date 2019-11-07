package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RulesActivity extends AppCompatActivity {
    Button okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        okay = findViewById(R.id.rules_ok_btn);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(RulesActivity.this, MenuActivity.class);
                RulesActivity.this.startActivity(activityChangeIntent);
            }

        });
    }
}
