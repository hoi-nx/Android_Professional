package com.heartofdead.base.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.heartofdead.base.interfaces.IViewMain;


/**
 * Created by nguyenxuanhoi2903 on 20/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements IViewMain {
    private boolean isDestroy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroy = false;
        setContentView(getLayoutMain());
        findViewByIds();
        initComponents();
        setEvents();
    }

}
