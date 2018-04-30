package com.heartofdead.base.base.adapter;

import android.view.View;

import java.util.List;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class HBaseRecyclerAdapter<T> extends HRecyclerAdapter<T,MViewHolder> {
    public HBaseRecyclerAdapter(List<T> ts) {
        super(ts);
    }
    @Override
    public MViewHolder buildHolder(View convertView, int viewType) {
        return new MViewHolder(convertView);
    }
}
