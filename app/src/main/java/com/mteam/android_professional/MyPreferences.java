package com.mteam.android_professional;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nguyenxuanhoi2903 on 21/11/2017.
 */

public class MyPreferences {
    private static final String MY_PREFERENCES = "MY_PREFERENCES";
    private SharedPreferences sharedPreferences;

    public MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void clearShare(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void putBoolean(String key, boolean isClick) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isClick);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putPerson(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void removeData(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
