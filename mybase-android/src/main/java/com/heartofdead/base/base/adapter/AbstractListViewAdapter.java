package com.heartofdead.base.base.adapter;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.heartofdead.base.interfaces.IBaseList;

/**
 * Created by Heart Of Dead on 13/01/2018.
 */

public abstract class AbstractListViewAdapter<I extends IBaseList,H,O> extends BaseAdapter{
    protected I mList;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_BODY = 1;
    public AbstractListViewAdapter(I mList) {
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
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public Object getItem(int position) {
        return mList.getData(position);
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

    public abstract View buildConvertView( int position, ViewGroup parent,int viewType);

    /**
     * Holder
     * @param itemView
     * @return
     */
    public abstract H buildHolder(View itemView,O t,int position);

    /**
     * @param holder
     * @param t
     * @param position
     */
    public abstract void bindViewDatas(H holder,O t,int position,int viewType);
}
