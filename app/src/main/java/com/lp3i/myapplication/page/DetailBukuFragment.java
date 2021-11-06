package com.lp3i.myapplication.page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ContohAdapter;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailBukuFragment extends Fragment {

    private Transaction transaction;
    private Buku buku;
    private Bundle bundle;

    public DetailBukuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate te layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_buku, container, false);

        ContohAdapter adapter = new ContohAdapter(new ArrayList<Buku>());

        TextView tvJudul = view.findViewById(R.id.tvJudul);
        ImageView ivBuku = view.findViewById(R.id.ivBuku);
        Button btnTambah = view.findViewById(R.id.btnTambah);

        MainActivity main = (MainActivity)requireActivity();

        bundle = main.getBundleActivity();
        buku = (Buku) bundle.getSerializable("data_buku");
        transaction = (Transaction) bundle.getSerializable("data_transaction");
        if (transaction == null){
            transaction = new Transaction();
        }

        tvJudul.setText( buku.getNamaBuku() );
        Picasso.get().load(buku.getUrlBuku() ).into(ivBuku);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Buku> bukus = transaction.getBukus();
                bukus.add(buku);
                transaction.setBukus(bukus);

                bundle.putSerializable("data_transaction", transaction);

                //seperti tombol back
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}