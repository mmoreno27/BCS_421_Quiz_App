package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput, passInput;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.email_Field);
        passInput = findViewById(R.id.pass_Field);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(emailInput.getText().toString())) {
                    emailInput.setError("This cannot be empty");
                }
                else if(TextUtils.isEmpty(passInput.getText().toString())) {
                    passInput.setError("This cannot be empty");
                }
                else if(Objects.equals(emailInput.getText().toString(), pref.getString("userEmail", null))
                        &&Objects.equals(passInput.getText().toString(), pref.getString("userPass", null)))
                {
                    editor.putInt("questNo", 1);
                    editor.commit();
                    Intent activityChangeIntent = new Intent(LoginActivity.this, RulesActivity.class);
                    LoginActivity.this.startActivity(activityChangeIntent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                }
            }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);
            }

        });
    }
}