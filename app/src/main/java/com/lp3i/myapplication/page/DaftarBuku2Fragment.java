package com.lp3i.myapplication.page;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ListBukuAdapter;
import com.lp3i.myapplication.model.ApiResponse;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;
import com.lp3i.myapplication.retrofit.APIClient;
import com.lp3i.myapplication.retrofit.APIInterface;
import com.lp3i.myapplication.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBuku2Fragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Bundle bundle;
    private Transaction transaction;
    CardView cardView;
    TextView tvHariPinjam;
    MyViewModel myViewModel;
    ArrayList<Buku> listBuku = new ArrayList<>();
    ListBukuAdapter adapter;

    /**
     * API
     */
    private APIInterface apiInterface;

    public DaftarBuku2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_daftar_buku, container, false);

        /**
         * API
         */
        apiInterface = APIClient.getClient().create(APIInterface.class);

        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        cardView = view.findViewById(R.id.cardview);
        tvHariPinjam = view.findViewById(R.id.tvHariPinjam);
        Button btnRefresh = view.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        adapter = new ListBukuAdapter(listBuku, new ListBukuAdapter.ListBukuListener() {
            @Override
            public void onClickItem(Buku buku) {
                gotoDetailBuku(buku);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

        getBook();

        /**
         * Observer Book
         */
        myViewModel.getBooks().observe(requireActivity(), new Observer<ArrayList<Buku>>() {
            @Override
            public void onChanged(ArrayList<Buku> bukus) {
                listBuku.clear();
                listBuku.addAll( bukus );
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void gotoDetailBuku(Buku buku){
        myViewModel.setDetailBook(buku);

        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new DetailBuku2Fragment() );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getBook(){
        apiInterface.getBuku().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                myViewModel.setBooks( response.body().data.data_buku );

                Log.d("DEBUGGG", "listbuku: "+listBuku.size());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("DEBUGGG", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

}