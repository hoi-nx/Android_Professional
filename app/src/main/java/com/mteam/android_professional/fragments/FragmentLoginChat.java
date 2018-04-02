package com.mteam.android_professional.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mteam.android_professional.Contants;
import com.mteam.android_professional.MyPreferences;
import com.mteam.android_professional.R;
import com.mteam.android_professional.Utils;
import com.stone.vega.library.VegaLayoutManager;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UnreadConversationCountListener;
import io.intercom.android.sdk.identity.Registration;

/**
 * Created by Stealer Of Souls on 3/30/2018.
 */

public class FragmentLoginChat extends Fragment {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_chat_activity,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initControl();
        initEvent();



    }
    private void initData() {
        myPreferences=new MyPreferences(getContext());
    }

    private void initEvent() {
        //Custom launcher
        getView().findViewById(R.id.messenger_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intercom.client().setLauncherVisibility(Intercom.Visibility.VISIBLE);
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(Contants.YOUR_APP_ID) || TextUtils.isEmpty(Contants.YOUR_API_KEY)) {
                    getView().findViewById(R.id.not_initialized).setVisibility(View.VISIBLE);
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
        edtEmail= (EditText) getView().findViewById(R.id.edt_email);
        unreadCountView = (TextView)getView(). findViewById(R.id.unread_counter);
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
    public void onPause() {
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
