package com.lp3i.myapplication.retrofit;

import com.lp3i.myapplication.model.ApiResponse;
import com.lp3i.myapplication.model.Buku;
import com.lp3i.myapplication.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("books")
    Call<ApiResponse> getBuku();

    @FormUrlEncoded
    @POST("login")
    Call<ApiResponse> postLogin(@Field("email") String email, @Field("password") String password);

    @GET("profile")
    Call<User> getProfile();

}
