package com.mteam.android_professional;

import android.app.Application;

import io.intercom.android.sdk.Intercom;

/**
 * Created by Stealer Of Souls on 3/30/2018.
 */

public class WellBeApplication extends Application {

    //----------------------------------------------------------------------------------------------
    // You need to enter your API key and app ID here to use Intercom for Android
    // To get these keys go to https://app.intercom.com/a/apps/_/settings/android and follow the step-up guide
    //----------------------------------------------------------------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();

        Intercom.initialize(this, Contants.YOUR_API_KEY, Contants.YOUR_APP_ID);
        Intercom.client().registerUnidentifiedUser();
    }

}
