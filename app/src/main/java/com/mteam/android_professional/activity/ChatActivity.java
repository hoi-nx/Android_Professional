package com.mteam.android_professional.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.mteam.android_professional.R;

import java.util.ArrayList;

/**
 * Created by Stealer Of Souls on 4/11/2018.
 */

public class ChatActivity extends AppCompatActivity  {
    NestedScrollView scrollView;
    private AppBarLayout appBarLayout;

    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat_message);
        scrollView= (NestedScrollView) findViewById(R.id.scroll);
        appBarLayout= (AppBarLayout) findViewById(R.id.app_bar);
       // scrollView.setNestedScrollingEnabled(true);

        scrollView.setSmoothScrollingEnabled(true);
//        initItems();
//        fabRevealMenu= (FABRevealMenu) findViewById(R.id.fabMenu);
//        img= (ImageView) findViewById(R.id.img_add);
//
//        try {
//            if (img != null && fabRevealMenu != null) {
//                //attach menu to fab
//               // fabRevealMenu.setFabMenu(fabMenu);
//                //set menu items from arrylist
//                fabRevealMenu.setMenuItems(items);
//                //attach menu to fab
//                fabRevealMenu.bindAnchorView(img);
//                //set menu item selection
//                fabRevealMenu.setOnFABMenuSelectedListener(this);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    }
//    private void initItems() {
//        items = new ArrayList<>();
//        items.add(new FABMenuItem("Attachments", AppCompatResources.getDrawable(this, R.drawable.ic_attachment)));
//        items.add(new FABMenuItem("Images", AppCompatResources.getDrawable(this, R.drawable.ic_image)));
//        items.add(new FABMenuItem("Places", AppCompatResources.getDrawable(this, R.drawable.ic_place)));
//        items.add(new FABMenuItem("Emoticons", AppCompatResources.getDrawable(this, R.drawable.ic_emoticon)));
//
//    }
//
//
//    @Override
//    public void onMenuItemSelected(View view, int id) {
//        if (id >= 0 && items != null && items.size() > id) {
//            Toast.makeText(this, items.get(id).getTitle() + "Clicked", Toast.LENGTH_SHORT).show();
//        }
//    }
}
