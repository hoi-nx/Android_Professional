package com.mteam.android_professional.interfaces;

import com.mteam.android_professional.obj.Lesson;

import java.util.List;

/**
 * Created by Stealer Of Souls on 2/4/2018.
 */

public interface IList {
    int count();

    Object getData(int position);
    void itemClick(int position);
}
