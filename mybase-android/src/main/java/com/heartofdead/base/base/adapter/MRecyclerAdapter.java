package com.heartofdead.base.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heartofdead.base.interfaces.IBaseList;

/**
 * Created by Heart Of Dead on 14/01/2018.
 */

public abstract class MRecyclerAdapter<I extends IBaseList,H extends RecyclerView.ViewHolder,O>extends RecyclerView.Adapter<H> {

    private I iList;
    public MRecyclerAdapter(I iList) {
        this.iList = iList;

    }
    @Override
    public int getItemCount() {
        return iList==null ? 0:iList.count();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = buildConvertView(parent,viewType);
        return buildHolder(itemView,viewType);
    }
    @Override
    public void onBindViewHolder(H holder, int position) {
        O o= position<iList.count() ? (O) iList.getData(position) : null;
        bindViewDatas(holder,o,position);
//        if(this.onItemClicklistener!=null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClicklistener.onItemClick(v,position);
//                }
//            });
        //}
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
    public abstract void bindViewDatas(H holder,O o,int position);

}
