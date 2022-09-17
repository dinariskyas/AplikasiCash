package com.dinariskyalinsaputri.aplikasicash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.dinariskyalinsaputri.aplikasicash.adapter.DetailCashAdapter;
import com.dinariskyalinsaputri.aplikasicash.helper.DatabaseHelper;
import com.dinariskyalinsaputri.aplikasicash.helper.DetailCash;
import com.dinariskyalinsaputri.aplikasicash.helper.User;

import java.util.ArrayList;
import java.util.List;

public class DetailCashActivity extends AppCompatActivity {

    //Declare RecyclerView
    private RecyclerView recyclerViewCashFlow;
    //Declare List
    private List<DetailCash> detailCashList;
    //Declare PatientRecyclerAdapter
    private DetailCashAdapter detailCashAdapter;
    //Declare DatabaseHelper
    private DatabaseHelper databaseHelper;

    Button btnKembaliDetailCashFlow;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash);

        try{

            if(getIntent().hasExtra("User")){
                user = getIntent().getParcelableExtra("User");
            }

            initViews();
            initObjects();

            detailCashList.clear();
            detailCashList.addAll(databaseHelper.getAllCashFlow());

            detailCashAdapter.notifyDataSetChanged();

            btnKembaliDetailCashFlow.setOnClickListener(v -> finish());
        }catch(Exception e){
            Log.d(" cash Flow activity", e.toString());
        }
    }

    private void initObjects() {
        detailCashList = new ArrayList<DetailCash>();
        detailCashAdapter = new DetailCashAdapter(detailCashList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCashFlow.setLayoutManager(mLayoutManager);
        recyclerViewCashFlow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCashFlow.setHasFixedSize(true);
        recyclerViewCashFlow.setAdapter(detailCashAdapter);

        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    private void initViews() {
        btnKembaliDetailCashFlow = findViewById(R.id.btnKembaliDetailCashFlow);
        recyclerViewCashFlow = findViewById(R.id.detail_cash_flow_recycler_view);
    }
}