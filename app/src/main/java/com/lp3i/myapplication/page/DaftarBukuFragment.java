package com.lp3i.myapplication.page;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ListBukuAdapter;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;
import com.lp3i.myapplication.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DaftarBukuFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Bundle bundle;
    private Transaction transaction;
    CardView cardView;
    TextView tvHariPinjam;
    MyViewModel myViewModel;

    public DaftarBukuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_daftar_buku, container, false);

        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        myViewModel.getTransactions().observe(requireActivity(), new Observer<Transaction>() {
            @Override
            public void onChanged(Transaction transactions) {
                refreshDataByViewModel(transactions);
            }
        });

        myViewModel.getTes().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String tes) {
                Log.d("DEBUGGG", "onChanged: "+tes);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        cardView = view.findViewById(R.id.cardview);
        tvHariPinjam = view.findViewById(R.id.tvHariPinjam);
        Button btnRefresh = view.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        refreshData();

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
                myViewModel.setTes("Helloo word");
                Log.d("DEBUGGG", "onClickItem: "+buku.getUrlBuku());

                ArrayList<Buku> bukus = new ArrayList<>();
                if ( myViewModel.getTransactions().getValue() == null ){
                    Transaction transaction1 = new Transaction();
                    myViewModel.setTransactions( transaction1 );
                } else {
                    bukus = myViewModel.getTransactions().getValue().getBukus();
                }

                bukus.add(buku);
                myViewModel.getTransactions().getValue().setBukus(bukus);
                myViewModel.setTransactions(myViewModel.getTransactions().getValue());

//                gotoDetailBuku( buku );

//                buku = (Buku) bundle.getSerializable("data_buku");
//                transaction = (Transaction) bundle.getSerializable("data_transaction");
//                if (transaction == null){
//                    transaction = new Transaction();
//                }
//
//                ArrayList<Buku> bukus = transaction.getBukus();
//                bukus.add(buku);
//                transaction.setBukus(bukus);
//
//                bundle.putSerializable("data_transaction", transaction);
//
//                refreshData();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

        return view;
    }

    private void refreshDataByViewModel(Transaction transactions) {
        if (transactions.getBukus().size() == 0) {
            cardView.setVisibility(View.GONE);
        } else {
            cardView.setVisibility(View.VISIBLE);
            tvHariPinjam.setText( transactions.getBukus().size() +" Buku dipilih" );
        }
    }

    private void refreshData() {
        MainActivity main = (MainActivity)requireActivity();
        bundle = main.getBundleActivity();
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