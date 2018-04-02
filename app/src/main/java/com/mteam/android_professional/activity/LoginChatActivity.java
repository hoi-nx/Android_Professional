package com.mteam.android_professional.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mteam.android_professional.Contants;
import com.mteam.android_professional.MyPreferences;
import com.mteam.android_professional.R;
import com.mteam.android_professional.Utils;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UnreadConversationCountListener;
import io.intercom.android.sdk.identity.Registration;

/**
 * Created by Stealer Of Souls on 3/30/2018.
 */

public class LoginChatActivity extends AppCompatActivity {
    //----------------------------------------------------------------------------------------------
    // Make sure you go to SampleApplication.java to set your app ID and API key
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // If you use Identity Verification you will need to include HMAC
    // We suggest taking these values from your app. You may need to change USER_ID above to match your HMAC
    //----------------------------------------------------------------------------------------------
    private EditText edtEmail;

    private TextView unreadCountView;
    private MyPreferences myPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_chat_activity);
      initData();
        initControl();
        initEvent();



        }

    private void initData() {
        myPreferences=new MyPreferences(this);
    }

    private void initEvent() {
        //Custom launcher
        findViewById(R.id.messenger_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intercom.client().setLauncherVisibility(Intercom.Visibility.VISIBLE);
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(Contants.YOUR_APP_ID) || TextUtils.isEmpty(Contants.YOUR_API_KEY)) {
                    findViewById(R.id.not_initialized).setVisibility(View.VISIBLE);
                } else {
                    //if you have provided a HMAC and data try begin secure session
                    if (!TextUtils.isEmpty(Utils.getHASH(edtEmail.getText().toString()))) {
                        Intercom.client().setUserHash(Utils.getHASH(edtEmail.getText().toString()));
                    }
                    //Intercom.client().registerIdentifiedUser(Registration.create().withUserId(edt.getText().toString()));
                    successfulLogin();
                    Intercom.client().displayConversationsList();

                }
            }
        });

        //set the unread count

        int unreadCount = Intercom.client().getUnreadConversationCount();
        unreadCountView.setText(String.valueOf(unreadCount));
        setBadgeVisibility(unreadCount, unreadCountView);
    }

    private void initControl() {
        edtEmail= (EditText) findViewById(R.id.edt_email);
        unreadCountView = (TextView) findViewById(R.id.unread_counter);
    }

    private void successfulLogin() {
    /* For best results, use a unique user_id if you have one. */
        Intercom.client().registerIdentifiedUser(Registration.create().withEmail(edtEmail.getText().toString()));
        myPreferences.putBoolean(Contants.LOGIN,true);
    }

    private final UnreadConversationCountListener unreadConversationCountListener = new UnreadConversationCountListener() {
        @Override public void onCountUpdate(int unreadCount) {
            setBadgeVisibility(unreadCount, unreadCountView);
            unreadCountView.setText(String.valueOf(unreadCount));
        }
    };

    private void setBadgeVisibility(int unreadCount, TextView unreadCountView) {
        int visibility = unreadCount == 0 ? View.INVISIBLE : View.VISIBLE;
        unreadCountView.setVisibility(visibility);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intercom.client().removeUnreadConversationCountListener(unreadConversationCountListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intercom.client().addUnreadConversationCountListener(unreadConversationCountListener);
      //  Intercom.client().handlePushMessage();
    }
}
