package com.dinariskyalinsaputri.aplikasicash.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinariskyalinsaputri.aplikasicash.DetailCashActivity;
import com.dinariskyalinsaputri.aplikasicash.R;
import com.dinariskyalinsaputri.aplikasicash.helper.DetailCash;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailCashAdapter extends RecyclerView.Adapter<DetailCashAdapter.DetailCashFlowViewHolder> {

    private List<DetailCash> detailCashList;
    String nominal = "";

    public DetailCashAdapter(List<DetailCash> detailCashList) {
        this.detailCashList = detailCashList;
    }

    @NonNull
    @Override
    public DetailCashAdapter.DetailCashFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_detail_cash, parent, false);

        return new DetailCashAdapter.DetailCashFlowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCashAdapter.DetailCashFlowViewHolder holder, int position) {
        holder.keteranganTv.setText(detailCashList.get(position).getKeterangan());
        holder.tanggalTv.setText(detailCashList.get(position).getTanggal()+"/"+ detailCashList.get(position).getBulan()+"/"+ detailCashList.get(position).getTahun());

        if(detailCashList.get(position).getTipe().contains("pemasukan")){
            nominal = "[+] Rp. "+ detailCashList.get(position).getNominal();
            holder.detailCashFlowIcon.setImageResource(R.drawable.ic_left_arrow);
        }else if(detailCashList.get(position).getTipe().contains("pengeluaran")) {
            nominal = "[-] Rp. "+ detailCashList.get(position).getNominal();
            holder.detailCashFlowIcon.setImageResource(R.drawable.ic_right_arrows);
        }
        holder.nominalTv.setText(nominal);

    }

    @Override
    public int getItemCount() {
        Log.d(DetailCashActivity.class.getSimpleName(), String.valueOf(detailCashList.size()));
        return detailCashList.size();
    }

    public class DetailCashFlowViewHolder extends RecyclerView.ViewHolder {

        public TextView nominalTv;
        public TextView keteranganTv;
        public TextView tanggalTv;
        public ImageView detailCashFlowIcon;


        public DetailCashFlowViewHolder(@NonNull View itemView) {
            super(itemView);

            nominalTv = itemView.findViewById(R.id.nominal_item_cash_flow);
            keteranganTv = itemView.findViewById(R.id.keterangan_item_cash_flow);
            tanggalTv = itemView.findViewById(R.id.tanggal_item_cash_flow);
            detailCashFlowIcon = itemView.findViewById(R.id.detail_cash_flow_image);
        }
    }
}
