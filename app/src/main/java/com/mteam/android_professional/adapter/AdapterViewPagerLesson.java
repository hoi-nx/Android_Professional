package com.mteam.android_professional.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mteam.android_professional.R;
import com.mteam.android_professional.interfaces.ICardAdapter;
import com.mteam.android_professional.interfaces.ICardView;
import com.mteam.android_professional.obj.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stealer Of Souls on 2/6/2018.
 */

public class AdapterViewPagerLesson extends PagerAdapter implements ICardAdapter{
    private float mBaseElevation;
    private List<CardView> mViews;
    private List<Lesson> mData;
    private ICardView iCardAdapter;


    public AdapterViewPagerLesson(ICardView iCardAdapter) {
        this.iCardAdapter=iCardAdapter;
        mViews = new ArrayList<>();
        mData=new ArrayList<>();
    }
    public void addCardItem(Lesson item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        if(position>mViews.size()){
            return null;
        }
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData==null?0:mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_lesson,container,false);
        container.addView(view);

        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        TextView txtName=view.findViewById(R.id.name_lesson);
        TextView tvDiscrip=view.findViewById(R.id.tvDiscrip);
        ImageView imgLock=view.findViewById(R.id.img_lock);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        Lesson lesson=mData.get(position);
        if(!lesson.isKey()){
            imgLock.setVisibility(View.VISIBLE);
        }
        txtName.setText(lesson.getNameLesson());
        tvDiscrip.setText(lesson.getDescription());
        mViews.set(position, cardView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCardAdapter.itemClick(position);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }
}
