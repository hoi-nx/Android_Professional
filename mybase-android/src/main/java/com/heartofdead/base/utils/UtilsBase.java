package com.heartofdead.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by nguyenxuanhoi2903 on 22/11/2017.
 */

public class UtilsBase {
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

    public static void openSettingNetwork(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        activity.startActivity(intent);
    }

    public static void openWifiSetting(Activity activity) {
        activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftInput(EditText edit, Context context) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edit, 0);
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    /**
     * @param context
     * @return boolean wifi
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * @param context
     * @return 3g
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     *wifi 2G 3G
     *
     * @param context
     * @return WF 2G 3G 4G
     */
    public static String getWifiOr2gOr3G(Context context) {
        String networkType = "";
        if (context != null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
                if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) {
                    networkType = activeNetInfo.getTypeName().toLowerCase();
                    if (networkType.equals("wifi")) {
                        networkType = "WF";
                    } else {
                        networkType = "2G";
                        int subType = activeNetInfo.getSubtype();
                        switch (subType) {
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_CDMA: // IS95
                                break;
                            case TelephonyManager.NETWORK_TYPE_EDGE: // 2.75
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_GPRS: // 2.5
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSDPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSPA: // 3.5
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                networkType = "3G";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                                networkType = "3G";
                                break; // ~ 1-2 Mbps
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                networkType = "3G";
                                break; // ~ 5 Mbps
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                networkType = "3G";
                                break; // ~ 10-20 Mbps
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                break; // ~25 kbps
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                networkType = "4G";
                                break; // ~ 10+ Mbps
                            default:
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return networkType;
    }

}
