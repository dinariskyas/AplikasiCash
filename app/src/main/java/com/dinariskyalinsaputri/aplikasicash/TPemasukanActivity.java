package com.dinariskyalinsaputri.aplikasicash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dinariskyalinsaputri.aplikasicash.helper.DatabaseHelper;
import com.dinariskyalinsaputri.aplikasicash.helper.DetailCash;
import com.dinariskyalinsaputri.aplikasicash.helper.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TPemasukanActivity extends AppCompatActivity {

    final Calendar myCalendarPemasukan = Calendar.getInstance();
    EditText editDatePemasukan, editNominalPemasukan, editTextKeterangan;
    ImageButton calendarPemasukan;

    Button btnSimpanPemasukan, btnKembaliPemasukan;

    DatePickerDialog.OnDateSetListener datePemasukan = null;

    DatabaseHelper databaseHelper = null;
    DetailCash dcf;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpemasukan);

        if(getIntent().hasExtra("User")){
            user = getIntent().getParcelableExtra("User");
        }

        initViews();
        initObjects();

    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(getApplicationContext());

        datePemasukan = (view, year, month, dayOfMonth) -> {
            myCalendarPemasukan.set(Calendar.YEAR, year);
            myCalendarPemasukan.set(Calendar.MONTH, month);
            myCalendarPemasukan.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        calendarPemasukan.setOnClickListener(v -> {
            new DatePickerDialog(TPemasukanActivity.this, datePemasukan, myCalendarPemasukan.get(Calendar.YEAR),myCalendarPemasukan.get(Calendar.MONTH),myCalendarPemasukan.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnKembaliPemasukan.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), BerandaActivity.class));
            finish();
        });


        btnSimpanPemasukan.setOnClickListener(v ->{

            dcf = new DetailCash();

            dcf.setNominal(Integer.parseInt(editNominalPemasukan.getText().toString()));
            dcf.setKeterangan(editTextKeterangan.getText().toString());
            dcf.setTanggal(String.valueOf(myCalendarPemasukan.get(Calendar.DAY_OF_MONTH)));
            dcf.setBulan(String.valueOf(myCalendarPemasukan.get(Calendar.MONTH)+1));
            dcf.setTahun(String.valueOf(myCalendarPemasukan.get(Calendar.YEAR)));
            dcf.setTipe("pemasukan");

            databaseHelper.addCashFlow(dcf);
            Toast.makeText(getApplicationContext(),"Pemasukan berhasil diinput!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), TPemasukanActivity.class));
            finish();
        });
    }

    private void updateLabel() {
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editDatePemasukan.setText(dateFormat.format(myCalendarPemasukan.getTime()));
    }

    private void initViews(){
        editDatePemasukan = findViewById(R.id.editDatePemasukan);
        calendarPemasukan = findViewById(R.id.ic_kalender_masuk);
        editNominalPemasukan = findViewById(R.id.editNominalPemasukan);
        editTextKeterangan = findViewById(R.id.editTextKeterangan);

        btnKembaliPemasukan = findViewById(R.id.btnKembaliPemasukan);
        btnSimpanPemasukan = findViewById(R.id.btnSimpanPemasukan);
    }
}