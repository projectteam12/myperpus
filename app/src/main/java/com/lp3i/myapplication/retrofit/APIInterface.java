package com.lp3i.myapplication.retrofit;

import com.lp3i.myapplication.model.ApiResponse;
import com.lp3i.myapplication.model.Buku;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("books")
    Call<ApiResponse> getBuku();
}
