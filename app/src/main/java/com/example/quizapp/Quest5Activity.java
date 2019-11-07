package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Quest5Activity extends AppCompatActivity {

    private Button submit;
    private TextView currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest5);

        final CheckBox checkbox1 = findViewById(R.id.q_5_check_1);
        final CheckBox checkbox3 = findViewById(R.id.q_5_check_3);
        final CheckBox checkbox4 = findViewById(R.id.q_5_check_4);
        final CheckBox checkbox5 = findViewById(R.id.q_5_check_5);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();

        int scoreInt = pref.getInt("userScore",0);

        submit = findViewById(R.id.submit_btn5);
        currentScore = findViewById(R.id.scoreDisplay5);
        currentScore.setText(String.valueOf(scoreInt));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Quest5Activity.this);
                builder1.setMessage("Are you sure you want to choose these answers?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (checkbox1.isChecked() && checkbox3.isChecked() && checkbox4.isChecked() && checkbox5.isChecked()) {
                                    int tempScore = pref.getInt("userScore", 0);
                                    tempScore++;
                                    editor.putInt("userScore", tempScore);
                                    editor.commit();
                                    Toast.makeText(Quest5Activity.this, "Correct!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent activityChangeIntent = new Intent(Quest5Activity.this, EndActivity.class);
                                    Quest5Activity.this.startActivity(activityChangeIntent);
                                } else {
                                    Toast.makeText(Quest5Activity.this, "One or more of your choices were incorrect!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent activityChangeIntent = new Intent(Quest5Activity.this, EndActivity.class);
                                    Quest5Activity.this.startActivity(activityChangeIntent);
                                }
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
}
