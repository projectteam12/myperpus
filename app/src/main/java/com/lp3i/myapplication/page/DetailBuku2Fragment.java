package com.lp3i.myapplication.page;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lp3i.myapplication.MainActivity;
import com.lp3i.myapplication.R;
import com.lp3i.myapplication.adapter.ContohAdapter;
import com.lp3i.myapplication.model.ApiResponse;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.Transaction;
import com.lp3i.myapplication.retrofit.APIClient;
import com.lp3i.myapplication.retrofit.APIInterface;
import com.lp3i.myapplication.viewmodel.MyViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBuku2Fragment extends Fragment {

    private Transaction transaction;
    MyViewModel myViewModel;
    /**
     * API
     */
    private APIInterface apiInterface;
    ProgressDialog dialog;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public DetailBuku2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate te layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_buku, container, false);

        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        dialog = new ProgressDialog(requireContext());
        dialog.setMessage("please wait..");

        /**
         * API
         */
        apiInterface = APIClient.getClient().create(APIInterface.class);

        final TextView tvJudul = view.findViewById(R.id.tvJudul);
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
                updateStok(myViewModel.getDetailBook().getValue().getId());
            }
        });

        return view;
    }

    private void refresh(){
        Log.d("DEBUGGG", "onClick: "+myViewModel.getDetailBook().getValue().getNamaBuku());;
        myViewModel.setSelectBooks(
                myViewModel.getDetailBook().getValue()
        );

        //seperti tombol back
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void updateStok(int id){
        apiInterface.updateStok(id).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                dialog.dismiss();
                refresh();

                Log.d("DEBUGGG", "listbuku: "+response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                dialog.dismiss();
                Log.d("DEBUGGG", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void gotoChecout(){
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new DetailBuku2Fragment() );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}