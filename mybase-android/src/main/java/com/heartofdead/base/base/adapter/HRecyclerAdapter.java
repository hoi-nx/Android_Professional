package com.heartofdead.base.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heartofdead.base.interfaces.IBaseList;

import java.util.List;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class HRecyclerAdapter<T,H extends RecyclerView.ViewHolder>extends RecyclerView.Adapter<H> {
    private List<T>tList;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BODY = 1;
    public HRecyclerAdapter(List<T> tList) {
        this.tList = tList;
    }

    @Override
    public int getItemCount() {
        return tList==null ? 0:tList.size();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = buildConvertView(parent,viewType);
        return buildHolder(itemView,viewType);
    }
    @Override
    public void onBindViewHolder(H holder, int position) {
        T o= position<tList.size() ? (T) tList.get(position) : null;
        bindViewDatas(holder,o,position,getItemViewType(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        return TYPE_BODY;
    }


    public View inflate(@LayoutRes int layoutId, ViewGroup parent, boolean attachToRoot){
        return LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,attachToRoot);
    }



    public abstract View buildConvertView( ViewGroup parent, int viewType);

    /**
     * Holder
     * @param convertView
     * @param viewType
     * @return
     */
    public abstract H buildHolder(View convertView,int viewType);

    /**
     *
     * @param holder
     * @param o
     * @param position
     */
    public abstract void bindViewDatas(H holder,T o,int position,int viewType);

}
