package com.lp3i.myapplication.page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lp3i.myapplication.R;

public class DetailBukuFragment extends Fragment {

    public DetailBukuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate te layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_buku, container, false);

        TextView tvBuku = view.findViewById(R.id.tvBuku);

        Bundle bundle = getArguments();

        tvBuku.setText( bundle.getString("judul") );

        return view;
    }
}