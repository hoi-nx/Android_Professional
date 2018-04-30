package com.heartofdead.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 28/12/2017.
 */

public class PermissionUtils {
    public static boolean checkPermisson(Context context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> listPermisson=new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            listPermisson.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(listPermisson.size()==0){
            return true;
        }
        int index=0;
        String permisson[]=new String[listPermisson.size()];
        for(String list:listPermisson){
            permisson[index]=list;
            index++;
        }
        ActivityCompat.requestPermissions((Activity) context,permisson,100);
        return false;
    }
}
