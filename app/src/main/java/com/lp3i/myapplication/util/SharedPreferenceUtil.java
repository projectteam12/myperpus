package com.lp3i.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lp3i.myapplication.BuildConfig;

public class SharedPreferenceUtil {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLogin(){
        editor.putBoolean("LOGIN", true);
        editor.commit();
    }

    public void removeLogin(){
        editor.putBoolean("LOGIN", false);
        editor.commit();
    }

    public boolean isLogged() {
        return sharedPreferences.getBoolean("LOGIN", false);
    }

}
