package com.lp3i.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.lp3i.myapplication.model.Buku;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContohAdapter extends RecyclerView.Adapter<ContohAdapter.MyViewHolder> {

    ArrayList<Buku> bukus = new ArrayList<>();

    public ContohAdapter() {
    }

    public ContohAdapter(ArrayList<Buku> bukus) {
        this.bukus = bukus;
    }

    @NonNull
    @Override
    public ContohAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContohAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
