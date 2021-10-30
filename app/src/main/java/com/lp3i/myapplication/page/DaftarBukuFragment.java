package com.lp3i.myapplication.page;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ListBukuAdapter;
import com.lp3i.myapplication.model.Buku;

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

        Bundle bundle = new Bundle();
        bundle.putString("judul", buku.getNamaBuku());

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