package com.heartofdead.base.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heartofdead.base.interfaces.IBaseList;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class MListViewAdapter<I extends IBaseList,H,O>extends AbstractListViewAdapter<I,H,O>{
    public MListViewAdapter(I mList) {
        super(mList);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        H holder = null;
        O o = (O) mList.getData(position);
        int viewType=getItemViewType(position);
        if(itemView==null){
            itemView = buildConvertView(position,parent,viewType);
            holder = buildHolder(itemView,o,position);
            itemView.setTag(holder);
        }else{
            holder = (H)itemView.getTag();
        }
        bindViewDatas(holder,o,position,viewType);
        return itemView;
    }



}
