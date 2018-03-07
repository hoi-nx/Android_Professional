package com.mteam.android_professional.interfaces;

/**
 * Created by Stealer Of Souls on 2/4/2018.
 */

public interface IList {
    int count();

    Object getData(int position);
    void itemClick(int position);
}
