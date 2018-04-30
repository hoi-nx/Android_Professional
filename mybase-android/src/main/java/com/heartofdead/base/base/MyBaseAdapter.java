package com.heartofdead.base.base;

import android.widget.BaseAdapter;

import com.heartofdead.base.interfaces.IBaseList;


/**
 * Created by nguyenxuanhoi2903 on 24/11/2017.
 */

public abstract class MyBaseAdapter<V extends IBaseList>extends BaseAdapter {
    protected V mList;

    public MyBaseAdapter(V mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.count();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mList.getData(position);
    }


}
