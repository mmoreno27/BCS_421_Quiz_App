package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText userFirst, userLast, userDOB, userEmail, userPass;
    Button complete;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userFirst = findViewById(R.id.firstname);
        userLast = findViewById(R.id.lastname);
        userDOB = findViewById(R.id.birthdate);
        userDOB.setInputType(InputType.TYPE_NULL);
        userEmail = findViewById(R.id.email_Field);
        userPass = findViewById(R.id.pass_Field);
        complete = findViewById(R.id.register);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();

        editor.putString("userEmail", "");
        editor.putString("userPass", "");
        editor.putInt("userScore", 0);
        editor.putInt("userGame", 0);

        userDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(complete.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                userDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(TextUtils.isEmpty(userFirst.getText().toString())) {
                    userFirst.setError("This cannot be empty");
                }
                else if(TextUtils.getTrimmedLength(userFirst.getText().toString()) < 3) {
                    userFirst.setError("Name is too short");
                }
                else if(TextUtils.getTrimmedLength(userFirst.getText().toString()) > 30) {
                    userFirst.setError("Name is too long");
                }
                else if(TextUtils.isEmpty(userLast.getText().toString())) {
                    userLast.setError("This cannot be empty");
                }
                else if(TextUtils.isEmpty(userDOB.getText().toString())) {
                    userDOB.setError("This cannot be empty");
                }
                else if(TextUtils.isEmpty(userEmail.getText().toString())) {
                    userEmail.setError("This cannot be empty");
                }
                else if (!userEmail.getText().toString().trim().matches(emailPattern)) {
                    userEmail.setError("Invalid Email Address");
                }
                else if(TextUtils.isEmpty(userPass.getText().toString())) {
                    userPass.setError("This cannot be empty");
                }
                else {
                    editor.putString("userEmail", userEmail.getText().toString());
                    editor.putString("userPass", userPass.getText().toString());
                    editor.commit();
                    Intent activityChangeIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(activityChangeIntent);
                }
            }
        });
    }
}
