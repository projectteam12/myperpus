package com.lp3i.myapplication.page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lp3i.myapplication.R;
import com.lp3i.myapplication.model.User;
import com.lp3i.myapplication.retrofit.APIClient;
import com.lp3i.myapplication.retrofit.APIInterface;
import com.lp3i.myapplication.util.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SharedPreferenceUtil sharedPreferenceUtil;

    /**
     * API
     */
    private APIInterface apiInterface;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        sharedPreferenceUtil = new SharedPreferenceUtil(requireContext());

        /**
         * API
         */
        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (sharedPreferenceUtil.isLogged()){
            fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new HomeFragment());
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new SignInFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiInterface.getProfile().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("DEBUGGG", "response: "+response.body().email);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("DEBUGGG", "onFailure: "+t.getLocalizedMessage());
                    }
                });

            }
        });

        return view;
    }
}