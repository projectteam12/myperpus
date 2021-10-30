package com.lp3i.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lp3i.myapplication.R;
import com.lp3i.myapplication.model.Buku;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListBukuAdapter extends RecyclerView.Adapter<ListBukuAdapter.MyViewHolder> {

    private ArrayList<Buku> bukuList;

    public interface ListBukuListener {
        void onClickItem(Buku buku);
    }

    ListBukuListener listBukuListener;

    public ListBukuAdapter(ArrayList<Buku> bukuList, ListBukuListener listBukuListener) {
        this.bukuList = bukuList;
        this.listBukuListener = listBukuListener;
    }

    @NonNull
    @Override
    public ListBukuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_list_buku, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBukuAdapter.MyViewHolder holder, final int position) {
        holder.tvNamaBuku.setText( bukuList.get(position).getNamaBuku() );
        holder.tvTahunTerbit.setText( bukuList.get(position).getTahunTerbit() );

        Picasso.get().load(bukuList.get(position).getUrlBuku() ).into(holder.ivBuku);

        holder.parentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBukuListener.onClickItem( bukuList.get(position) );
            }
        });
    }

    @Override
    public int getItemCount() {
        return bukuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBuku;
        TextView tvNamaBuku;
        TextView tvTahunTerbit;
        LinearLayout parentItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaBuku = itemView.findViewById(R.id.tvNamaBuku);
            tvTahunTerbit = itemView.findViewById(R.id.tvTahunTerbit);
            parentItem = itemView.findViewById(R.id.parentItem);
            ivBuku = itemView.findViewById(R.id.ivBuku);

        }
    }
}
