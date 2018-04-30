package com.heartofdead.base.base.adapter;

import android.view.View;

import com.heartofdead.base.interfaces.IBaseList;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class MBaseListViewListViewAdapter<T extends IBaseList,O>extends MListViewAdapter<T,MViewHolder,O> {
    public MBaseListViewListViewAdapter(T mList) {
        super(mList);
    }

    @Override
    public MViewHolder buildHolder(View convertView, O t, int position) {
        return new MViewHolder(convertView);
    }
}
