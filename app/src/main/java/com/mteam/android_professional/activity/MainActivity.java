package com.mteam.android_professional.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.mteam.android_professional.Contants;
import com.mteam.android_professional.MyPreferences;
import com.mteam.android_professional.R;
import com.mteam.android_professional.Utils;
import com.mteam.android_professional.fragments.FragmentChapter;
import com.mteam.android_professional.fragments.FragmentLesson;
import com.mteam.android_professional.fragments.FragmentLoginChat;
import com.mteam.android_professional.obj.Chapter;

import java.util.Random;

import io.intercom.android.sdk.Intercom;

public class MainActivity extends AppCompatActivity {
    private FragmentChapter mFragmentChapter;
    private KenBurnsView mKenBurnsView;
    private TextView tvName,tvMail,tvAboutUs,tvShare,tvChat,tvRatting,tvLanguage,tvVersion;
    private DrawerLayout drawerLayout;
    private RelativeLayout mLayout,mAboutMe;
    private MyPreferences myPreferences;
    private ImageView imgLogout;

    private Typeface monterLight,mLight,monterRegular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        initComponent();
        initEvents();

    }

    private void initView() {
        mKenBurnsView = (KenBurnsView) findViewById(R.id.mKenburns);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLayout= (RelativeLayout) findViewById(R.id.layout_send_message);
        imgLogout= (ImageView) findViewById(R.id.img_logout);
        mAboutMe= (RelativeLayout) findViewById(R.id.layout_about);
        tvName= (TextView) findViewById(R.id.tv_name);
        tvMail= (TextView) findViewById(R.id.tv_gmail);

        tvAboutUs= (TextView) findViewById(R.id.tv_about_us);
        tvChat= (TextView) findViewById(R.id.tv_send_message);
        tvRatting= (TextView) findViewById(R.id.tv_rate);
        tvShare= (TextView) findViewById(R.id.tv_share);
        tvLanguage= (TextView) findViewById(R.id.tv_language);
        tvVersion= (TextView) findViewById(R.id.tv_version);

        tvName.setTypeface(monterRegular);
        tvMail.setTypeface(monterLight);

        tvShare.setTypeface(mLight);
        tvAboutUs.setTypeface(mLight);
        tvChat.setTypeface(mLight);
        tvRatting.setTypeface(mLight);
        tvLanguage.setTypeface(mLight);
        tvVersion.setTypeface(mLight);
    }

    private void initEvents() {
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.update_version,Toast.LENGTH_LONG).show();
            }
        });


        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLogout.setVisibility(View.GONE);
                myPreferences.putBoolean(Contants.LOGIN,false);
                Intercom.client().logout();
            }
        });

        mAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initComponent() {
        // randomBackground();
        FragmentManager manager = getFragmentManager();
        mFragmentChapter = new FragmentChapter();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_content, mFragmentChapter, FragmentChapter.class.getName());
        //transaction.addToBackStack(FragmentChapter.class.getName());
        transaction.commit();
    }

    public void openMenu() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void randomBackground() {
        Random random = new Random();
        int index = random.nextInt(2);
        switch (index) {
            case 0:
                //  mKenBurnsView.setImageResource(R.drawable.ic_action_background_five);
                break;
            case 1:
                //   mKenBurnsView.setImageResource(R.drawable.ic_action_background_two);
                break;
            default:
                break;
        }
    }

    private void initData() {
        myPreferences=new MyPreferences(this);
        monterRegular= Typeface.createFromAsset(getAssets(),"fonts/montserrat_regular.otf");
        monterLight=Typeface.createFromAsset(getAssets(),"fonts/montserrat_extralight.otf");
        mLight=Typeface.createFromAsset(getAssets(),"fonts/montserrat_light.otf");

    }

    public void openFragmentLesson(Chapter chapter) {
        FragmentManager manager = getFragmentManager();
        //kiem tra fragment login da ton tai trong fragmentmanager chua
        FragmentLesson fragment =
                (FragmentLesson) manager.findFragmentByTag(FragmentLesson.class.getName());
        //if fragment == null thi fragment chua co trong fragmentmanager
        if (fragment != null) {
            if (fragment.isVisible()) {

            } else {
                FragmentTransaction transaction = manager.beginTransaction();
//                transaction .setCustomAnimations(
//                        R.animator.card_flip_right_in,
//                        R.animator.card_flip_right_out,
//                        R.animator.card_flip_left_in,
//                        R.animator.card_flip_left_out);
                transaction.replace(R.id.frame_content, fragment, FragmentLesson.class.getName());
                transaction.addToBackStack(FragmentLesson.class.getName());

                transaction.commit();
            }
            return;
        }

        //tao ra login fragment va them vao
        fragment = new FragmentLesson(chapter);
        manager.beginTransaction().
                setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out).replace(R.id.frame_content, fragment, FragmentLesson.class.getName()).addToBackStack(FragmentLesson.class.getName()).commit();
    }


}
