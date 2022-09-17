package com.dinariskyalinsaputri.aplikasicash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinariskyalinsaputri.aplikasicash.helper.DatabaseHelper;
import com.dinariskyalinsaputri.aplikasicash.helper.DetailCash;
import com.dinariskyalinsaputri.aplikasicash.helper.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BerandaActivity extends AppCompatActivity {

    ImageView nextPengaturan, nextPemasukan, nextPengeluaran, nextDetailCash;

    TextView totalPemasukanBulanIni, totalPengeluaranBulanIni;

    DatabaseHelper databaseHelper = null;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        DateFormat dateFormat = new SimpleDateFormat("MM", Locale.US);
        Date date = new Date();

        String currentMonth = dateFormat.format(date);
        Log.d("Current month", currentMonth);

        if(getIntent().hasExtra("User")){
            user = getIntent().getParcelableExtra("User");
        }

        initViews();
        initObjects(currentMonth);

    }

    private void initViews(){
        nextPengaturan = findViewById(R.id.nextSetting);
        nextPemasukan = findViewById(R.id.nextPemasukan);
        nextPengeluaran = findViewById(R.id.nextPengeluaran);
        nextDetailCash = findViewById(R.id.nextDetailCash);

        totalPemasukanBulanIni = findViewById(R.id.totalPemasukanBulanIni);
        totalPengeluaranBulanIni = findViewById(R.id.totalPengeluaranBulanIni);
    }

    private void initObjects(String currentMonth){

        databaseHelper = new DatabaseHelper(getApplicationContext());

        nextPengaturan.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), PengaturanActivity.class);
            i.putExtra("User",user);
            startActivity(i);
        });

        nextPemasukan.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), TPemasukanActivity.class);
            i.putExtra("User",user);
            startActivity(i);
        });

        nextPengeluaran.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), TPengeluaranActivity.class);
            i.putExtra("User",user);
            startActivity(i);
        });

        nextDetailCash.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), DetailCashActivity.class);
            i.putExtra("User",user);
            startActivity(i);
        });

        if(databaseHelper.getNominalPemasukanByBulan(currentMonth) != null){

            int pemasukan = 0;
            List<DetailCash> list = databaseHelper.getNominalPemasukanByBulan(currentMonth);

            for (int i = 0; i < list.size(); i++) {
                pemasukan += list.get(i).getNominal();
            }
            totalPemasukanBulanIni.setText("Rp. "+ pemasukan);
            Log.d("Pemasukan", ""+pemasukan);
        }else{
            totalPemasukanBulanIni.setText("Rp. 0");
        }

        if(databaseHelper.getNominalPengeluaranByBulan(currentMonth) != null) {

            int pengeluaran = 0;
            List<DetailCash> list = databaseHelper.getNominalPengeluaranByBulan(currentMonth);

            for (int i = 0; i < list.size(); i++) {
                pengeluaran += list.get(i).getNominal();
            }

            totalPengeluaranBulanIni.setText("Rp. "+pengeluaran);
            Log.d("Pengeluaran", ""+pengeluaran);

        }else{
            totalPengeluaranBulanIni.setText("Rp. 0");
        }
    }
}