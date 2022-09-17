package com.dinariskyalinsaputri.aplikasicash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dinariskyalinsaputri.aplikasicash.helper.DatabaseHelper;
import com.dinariskyalinsaputri.aplikasicash.helper.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "MyPreferences";
    TextInputLayout edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLog = edtUsername.getEditText().getText().toString();
                String passwordLog = edtPassword.getEditText().getText().toString();

                SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String prefsUsername = preferences.getString("username", "user");
                String prefsPassword = preferences.getString("password", "user");

                if (usernameLog.equals(prefsUsername) && passwordLog.equals(prefsPassword)) {
                    startActivity(new Intent(LoginActivity.this, BerandaActivity.class));
                } else {
                    Toast.makeText(getBaseContext(), "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}