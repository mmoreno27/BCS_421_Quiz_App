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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quest1Activity extends AppCompatActivity {

    private RadioGroup radioQ1Group;
    private RadioButton radioQ1Button;
    private Button submit;
    private TextView currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest1);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putInt("userScore", 0);
        editor.commit();

        int scoreInt = pref.getInt("userScore",0);

        radioQ1Group = findViewById(R.id.radioGroup_1);
        submit = findViewById(R.id.submit_btn1);
        currentScore = findViewById(R.id.scoreDisplay1);
        currentScore.setText(String.valueOf(scoreInt));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioQ1Group.getCheckedRadioButtonId();
                radioQ1Button = (RadioButton) findViewById(selectedId);

                if (radioQ1Group.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Quest1Activity.this, "Please select an answer",
                            Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Quest1Activity.this);
                    builder1.setMessage("Are you sure you want to choose " + radioQ1Button.getText().toString() + "?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (radioQ1Button.getText().toString().matches("2")) {
                                        int tempScore = pref.getInt("userScore", 0);
                                        tempScore++;
                                        editor.putInt("userScore", tempScore);
                                        editor.commit();
                                        Toast.makeText(Quest1Activity.this, "Correct!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent activityChangeIntent = new Intent(Quest1Activity.this, Quest2Activity.class);
                                        Quest1Activity.this.startActivity(activityChangeIntent);
                                    } else {
                                        Toast.makeText(Quest1Activity.this, "Incorrect!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent activityChangeIntent = new Intent(Quest1Activity.this, Quest2Activity.class);
                                        Quest1Activity.this.startActivity(activityChangeIntent);
                                    }
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }
                    );
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }
}
