package com.lp3i.myapplication.page;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ListBukuAdapter;
import com.lp3i.myapplication.model.ApiResponse;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.retrofit.APIClient;
import com.lp3i.myapplication.retrofit.APIInterface;
import com.lp3i.myapplication.viewmodel.MyViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutFragment extends Fragment {

    MyViewModel myViewModel;
    ProgressDialog dialog;
    ListBukuAdapter adapter;
    ArrayList<Buku> listBuku = new ArrayList<>();
    /**
     * API
     */
    private APIInterface apiInterface;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        /**
         * API
         */
        apiInterface = APIClient.getClient().create(APIInterface.class);

        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        dialog = new ProgressDialog(requireContext());
        dialog.setMessage("please wait..");

        listBuku.addAll( myViewModel.getSelectBooks().getValue() );

        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(listBuku);
            }
        });

        adapter = new ListBukuAdapter(listBuku, new ListBukuAdapter.ListBukuListener() {
            @Override
            public void onClickItem(Buku buku) {


            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter( adapter );

        // Inflate the layout for this fragment
        return view;
    }

    private void save(ArrayList<Buku> bukus){
        dialog.show();
        apiInterface.saveTransaction(bukus).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                dialog.dismiss();

                Log.d("DEBUGGG", "transaction: "+response.body().message);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                dialog.dismiss();
                Log.d("DEBUGGG", "transaction: "+t.getLocalizedMessage());
            }
        });
    }

}