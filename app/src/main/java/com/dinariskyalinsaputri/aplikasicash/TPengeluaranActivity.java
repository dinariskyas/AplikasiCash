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

public class TPengeluaranActivity extends AppCompatActivity {

    EditText editDatePengeluaran, editNominalPengeluaran, editKeteranganPengeluaran;
    Button btnSimpanPengeluaran, btnKembaliPengeluaran;
    ImageButton calendarPengeluaran;


    final Calendar myCalendarPengeluaran = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePengeluaran = null;

    DatabaseHelper databaseHelper = null;
    DetailCash dcf;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpengeluaran);

        if(getIntent().hasExtra("User")){
            user = getIntent().getParcelableExtra("User");
        }

        initViews();
        initObjects();


    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(getApplicationContext());

        datePengeluaran = (view, year, month, dayOfMonth) -> {
            myCalendarPengeluaran.set(Calendar.YEAR, year);
            myCalendarPengeluaran.set(Calendar.MONTH, month);
            myCalendarPengeluaran.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        calendarPengeluaran.setOnClickListener(v -> {
            new DatePickerDialog(TPengeluaranActivity.this, datePengeluaran, myCalendarPengeluaran.get(Calendar.YEAR),myCalendarPengeluaran.get(Calendar.MONTH),myCalendarPengeluaran.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnKembaliPengeluaran.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), BerandaActivity.class));
            finish();
        });


        btnSimpanPengeluaran.setOnClickListener(v ->{

            dcf = new DetailCash();

            dcf.setNominal(Integer.parseInt(editNominalPengeluaran.getText().toString()));
            dcf.setTanggal(String.valueOf(myCalendarPengeluaran.get(Calendar.DAY_OF_MONTH)));
            dcf.setKeterangan(editKeteranganPengeluaran.getText().toString());
            dcf.setBulan(String.valueOf(myCalendarPengeluaran.get(Calendar.MONTH)+1));
            dcf.setTahun(String.valueOf(myCalendarPengeluaran.get(Calendar.YEAR)));
            dcf.setTipe("pengeluaran");

            databaseHelper.addCashFlow(dcf);
            Toast.makeText(getApplicationContext(),"Pengeluaran berhasil diinput!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), TPengeluaranActivity.class));
            finish();
        });
    }

    private void updateLabel() {
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editDatePengeluaran.setText(dateFormat.format(myCalendarPengeluaran.getTime()));
    }

    private void initViews(){
        editDatePengeluaran = findViewById(R.id.editDatePengeluaran);
        calendarPengeluaran = findViewById(R.id.icon_pengeluaran_kalender);
        btnKembaliPengeluaran = findViewById(R.id.btnKembaliPengeluaran);
        editKeteranganPengeluaran = findViewById(R.id.editKeteranganPengeluaran);

        editNominalPengeluaran = findViewById(R.id.editNominalPengeluaran);
        btnSimpanPengeluaran = findViewById(R.id.btnSimpanPengeluaran);
    }
}