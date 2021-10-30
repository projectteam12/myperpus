package com.lp3i.myapplication.page;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lp3i.myapplication.R;
import com.lp3i.myapplication.util.SharedPreferenceUtil;

public class HomeFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SharedPreferenceUtil sharedPreferenceUtil;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferenceUtil = new SharedPreferenceUtil(requireContext());

        CardView btnPengaturan = view.findViewById(R.id.btnPengaturan);
        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("Logout ya ga?");
            }
        });

        CardView btnDaftarBuku = view.findViewById(R.id.btnDaftarBuku);
        btnDaftarBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDaftarBuku();
            }
        });

        return view;
    }

    private void gotoMain(){
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MainFragment());
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void gotoDaftarBuku(){
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new DaftarBukuFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showAlert(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferenceUtil.removeLogin();
                gotoMain();
            }
        });

        builder1.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder1.create();
        alert.show();
    }
}