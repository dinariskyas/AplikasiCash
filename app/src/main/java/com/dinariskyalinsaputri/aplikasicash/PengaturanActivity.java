package com.dinariskyalinsaputri.aplikasicash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dinariskyalinsaputri.aplikasicash.helper.DatabaseHelper;
import com.dinariskyalinsaputri.aplikasicash.helper.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class PengaturanActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPreferences";

    TextInputLayout edtPasswordSaatIni, edtPasswordBaru;
    Button btnSimpanSetting, btnKembaliSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        edtPasswordBaru = findViewById(R.id.edtPasswordBaru);
        edtPasswordSaatIni = findViewById(R.id.edtPasswordSaatIni);
        btnSimpanSetting = findViewById(R.id.btnSimpanSetting);

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String password = preferences.getString("password", "user");
        btnSimpanSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPasswordSaatIni.getEditText().getText().toString().equals(password)) {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("username", "user");
                    editor.putString("password", edtPasswordBaru.getEditText().getText().toString());
                    editor.apply();
                    Toast.makeText(getBaseContext(), "Password berhasil diubah!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void handleBack(View view) {
        Intent intent = new Intent(this, BerandaActivity.class);
        startActivity(intent);
    }
}