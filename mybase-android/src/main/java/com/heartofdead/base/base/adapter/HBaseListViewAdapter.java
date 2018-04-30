package com.heartofdead.base.base.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class HBaseListViewAdapter<T> extends HAdapter<MViewHolder,T> {

    public HBaseListViewAdapter(List<T> listData) {
        super(listData);
    }

    @Override
    public MViewHolder buildHolder(View itemView, T t, int position) {
        return new MViewHolder(itemView);
    }
}
