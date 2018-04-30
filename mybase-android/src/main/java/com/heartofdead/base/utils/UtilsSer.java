package com.heartofdead.base.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Heart Of Dead on 28/12/2017.
 */

public class UtilsSer {
    public static String getLinkSer(String link) {
        String data = "";

        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            InputStreamReader strem = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(strem);
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return data;
    }



}
