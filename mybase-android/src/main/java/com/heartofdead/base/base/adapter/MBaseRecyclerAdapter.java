package com.heartofdead.base.base.adapter;

import android.view.View;

import com.heartofdead.base.interfaces.IBaseList;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class MBaseRecyclerAdapter<I extends IBaseList,O>extends MRecyclerAdapter<I,MViewHolder,O> {
    public MBaseRecyclerAdapter(I iList) {
        super(iList);
    }

    @Override
    public MViewHolder buildHolder(View convertView, int viewType) {
        return new MViewHolder(convertView);
    }
}
