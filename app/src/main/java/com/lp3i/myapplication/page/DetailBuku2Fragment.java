package com.lp3i.myapplication.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ContohAdapter;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;
import com.lp3i.myapplication.viewmodel.MyViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailBuku2Fragment extends Fragment {

    private Transaction transaction;
    MyViewModel myViewModel;

    public DetailBuku2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate te layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_buku, container, false);

        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        TextView tvJudul = view.findViewById(R.id.tvJudul);
        ImageView ivBuku = view.findViewById(R.id.ivBuku);
        Button btnTambah = view.findViewById(R.id.btnTambah);

        myViewModel.getDetailBook().observe(requireActivity(), new Observer<Buku>() {
            @Override
            public void onChanged(Buku buku) {
                tvJudul.setText( buku.getNamaBuku() );
//                Picasso.get().load(buku.getUrlBuku() ).into(ivBuku);
            }
        });


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seperti tombol back
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}