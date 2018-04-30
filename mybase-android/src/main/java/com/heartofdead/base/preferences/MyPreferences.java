package com.heartofdead.base.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nguyenxuanhoi2903 on 21/11/2017.
 */

public class MyPreferences {
    private static final String MY_PREFERENCES = "MY_PREFERENCES";
    private Context context;
    private SharedPreferences sharedPreferences;

    public MyPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void putBooleanClickIntro(String key, boolean isClick) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isClick);
        editor.commit();
    }

    public boolean getBooleanClickIntro(String key) {
        return sharedPreferences.getBoolean(key, false);
    }


}
