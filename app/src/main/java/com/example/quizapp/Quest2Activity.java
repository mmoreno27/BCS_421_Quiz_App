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

public class Quest2Activity extends AppCompatActivity {

    private RadioGroup radioQ2Group;
    private RadioButton radioQ2Button;
    private Button submit;
    private TextView currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest2);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();

        int scoreInt = pref.getInt("userScore",0);

        radioQ2Group = findViewById(R.id.radioGroup_2);
        submit = findViewById(R.id.submit_btn2);
        currentScore = findViewById(R.id.scoreDisplay2);
        currentScore.setText(String.valueOf(scoreInt));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioQ2Group.getCheckedRadioButtonId();
                radioQ2Button = (RadioButton) findViewById(selectedId);

                if (radioQ2Group.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Quest2Activity.this, "Please select an answer",
                            Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Quest2Activity.this);
                    builder1.setMessage("Are you sure you want to choose " + radioQ2Button.getText().toString() + "?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (radioQ2Button.getText().toString().matches("dog")) {
                                        int tempScore = pref.getInt("userScore", 0);
                                        tempScore++;
                                        editor.putInt("userScore", tempScore);
                                        editor.commit();
                                        Toast.makeText(Quest2Activity.this, "Correct!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent activityChangeIntent = new Intent(Quest2Activity.this, Quest3Activity.class);
                                        Quest2Activity.this.startActivity(activityChangeIntent);
                                    } else {
                                        Toast.makeText(Quest2Activity.this, "Incorrect!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent activityChangeIntent = new Intent(Quest2Activity.this, Quest3Activity.class);
                                        Quest2Activity.this.startActivity(activityChangeIntent);
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
            }
        });
    }
}
