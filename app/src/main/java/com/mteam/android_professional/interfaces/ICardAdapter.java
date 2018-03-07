package com.mteam.android_professional.interfaces;


import android.support.v7.widget.CardView;

public interface ICardAdapter {


    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
