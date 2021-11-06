package com.lp3i.myapplication.page;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ListBukuAdapter;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;

import java.util.ArrayList;
import java.util.Random;

public class DaftarBukuFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public DaftarBukuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_buku, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CardView cardView = view.findViewById(R.id.cardview);
        TextView tvHariPinjam = view.findViewById(R.id.tvHariPinjam);

        MainActivity main = (MainActivity)requireActivity();

        Bundle bundle = main.getBundleActivity();
        if (bundle != null){
            Transaction transaction = (Transaction) bundle.getSerializable("data_transaction");
            if (transaction == null){
                cardView.setVisibility(View.GONE);
            }else {
                cardView.setVisibility(View.VISIBLE);

                tvHariPinjam.setText( transaction.getBukus().size() +" Buku dipilih" );

            }

        } else {
            cardView.setVisibility(View.GONE);
        }


        ArrayList<Buku> listBuku = new ArrayList<>();
        Buku buku = new Buku(1, "Android Dasar", "2021", "Joko Anwar", "ini buku android", "IT", 10, "https://dummyimage.com/100x100/"+randomColor()+"/fff.png&text=");
        buku.setUrlBuku( buku.getUrlBuku() + buku.getNamaBuku().substring(0,1) );
        listBuku.add( buku );

        buku = new Buku(1, "Pyhton Dasar", "2021", "Joko Anwar", "ini buku android", "IT", 10, "https://dummyimage.com/100x100/"+randomColor()+"/fff.png&text=");
        buku.setUrlBuku( buku.getUrlBuku() + buku.getNamaBuku().substring(0,1) );
        listBuku.add( buku );

        buku = new Buku(1, "Netbeans Dasar", "2021", "Joko Anwar", "ini buku android", "IT", 10, "https://dummyimage.com/100x100/"+randomColor()+"/fff.png&text=");
        buku.setUrlBuku( buku.getUrlBuku() + buku.getNamaBuku().substring(0,1) );
        listBuku.add( buku );

        buku = new Buku(1, "VB Dasar", "2021", "Joko Anwar", "ini buku android", "IT", 10, "https://dummyimage.com/100x100/"+randomColor()+"/fff.png&text=");
        buku.setUrlBuku( buku.getUrlBuku() + buku.getNamaBuku().substring(0,1) );
        listBuku.add( buku );

        buku = new Buku(1, "JAVA Dasar", "2021", "Joko Anwar", "ini buku android", "IT", 10, "https://dummyimage.com/100x100/"+randomColor()+"/fff.png&text=");
        buku.setUrlBuku( buku.getUrlBuku() + buku.getNamaBuku().substring(0,1) );
        listBuku.add( buku );

        ListBukuAdapter adapter = new ListBukuAdapter(listBuku, new ListBukuAdapter.ListBukuListener() {
            @Override
            public void onClickItem(Buku buku) {
                gotoDetailBuku( buku );
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

        return view;
    }

    private void gotoDetailBuku(Buku buku){

        MainActivity main = (MainActivity)requireActivity();

        Bundle bundle = main.getBundleActivity();
        bundle.putSerializable("data_buku", buku);

        Fragment fragment = new DetailBukuFragment();
        fragment.setArguments(bundle);

        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private int randomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
}