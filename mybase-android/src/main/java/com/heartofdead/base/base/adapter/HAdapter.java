package com.heartofdead.base.base.adapter;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.heartofdead.base.interfaces.IBaseList;

import java.util.List;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class HAdapter<H,T>extends BaseAdapter{
    private List<T>listData;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BODY = 1;
    public HAdapter(List<T> listData) {
        this.listData = listData;
    }
    @Override
    public int getCount() {
        return listData==null?0:listData.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        return TYPE_BODY;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public View inflate(@LayoutRes int layoutId, ViewGroup parent, boolean attachToRoot){
        return LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,attachToRoot);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        H holder = null;
        T o = (T) listData.get(position);
        int viewType=getItemViewType(position);
        if(itemView==null){
//            switch (viewType){
//                case TYPE_HEADER:
//                    itemView = buildConvertView(position,parent,viewType);
//                    holder = buildHolder(itemView,o,position);
//                    break;
//                case TYPE_BODY:
//                    itemView = buildConvertView(position,parent,viewType);
//                    holder = buildHolder(itemView,o,position);
//                    break;
//            }
            itemView = buildConvertView(position,parent,viewType);
            holder = buildHolder(itemView,o,position);
            itemView.setTag(holder);
        }else{
            holder = (H)itemView.getTag();
        }
        bindViewDatas(holder,o,position,viewType);
        return itemView;
    }



    public abstract View buildConvertView( int position, ViewGroup parent,int viewType);

    /**
     * Holder
     * @param itemView
     * @return
     */
    public abstract H buildHolder(View itemView,T t,int position);

    /**
     * @param holder
     * @param t
     * @param position
     */
    public abstract void bindViewDatas(H holder,T t,int position,int viewType);
}
