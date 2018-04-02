package com.mteam.android_professional;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mteam.android_professional.obj.Chapter;
import com.mteam.android_professional.obj.Lesson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Stealer Of Souls on 2/19/2018.
 */

public class Utils {
    public static String readFileFromAssets(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static List<Lesson> getLessonList(Chapter chapter,Context context) {

        if (chapter != null) {
            int idChapter = chapter.getIdChapter();
            String jsonString = Utils.readFileFromAssets("data/lesson_android_c" + idChapter + ".json", context);
            if (!jsonString.isEmpty() && jsonString != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Lesson>>() {
                }.getType();
                return gson.fromJson(jsonString, type);
            }

        }
        return null;
    }

    private static String toHexString(final byte[] bytes) {
        final Formatter formatter = new Formatter();
        for (final byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String hmacSha256(final String key, final String s) {
        try {
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
            return toHexString(mac.doFinal(s.getBytes()));
        } catch (final Exception e) {
            // ...
        }
        return null;
    }
    public static String getHASH(String email) {
        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(Contants.IDENTITY_VERIFICATION_SECRET.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] hash = (sha256_HMAC.doFinal(Contants.IDENTITY_VERIFICATION_SECRET.getBytes()));
            StringBuffer result = new StringBuffer();
            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
    public static boolean checkInternetConnection(Context context) {
        try {
            ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cManager.getActiveNetworkInfo();
            if (nInfo != null && nInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

}
