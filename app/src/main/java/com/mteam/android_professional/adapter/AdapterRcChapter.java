package com.mteam.android_professional.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mteam.android_professional.Utils;
import com.mteam.android_professional.interfaces.IList;
import com.mteam.android_professional.R;
import com.mteam.android_professional.obj.Chapter;
import com.mteam.android_professional.obj.Lesson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Stealer Of Souls on 2/4/2018.
 */

public class AdapterRcChapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IList iList;
    private List<Lesson>lessonList;

    public AdapterRcChapter(IList iList) {
        this.iList = iList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MViewHolder mViewHolder = (MViewHolder) holder;
        Chapter chapter = (Chapter) iList.getData(position);
        setImageFromAssets(mViewHolder.imgChapter,mViewHolder.itemView.getContext(),"images/"+chapter.getImgChapter());
        mViewHolder.tvName.setText(chapter.getNameChapter());
        mViewHolder.tvDiscription.setText(chapter.getDiscription());
        Log.d("", "onBindViewHolder: "+chapter.getIdChapter());


        mViewHolder.tvNumberLesson.setText(iList.countLesson(position)+"");

        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iList.itemClick(position);
            }
        });

    }

    public void setImageFromAssets(ImageView mImage, Context context,String nameImage) {
        try {
            // get input stream
            InputStream ims = context.getAssets().open(nameImage);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mImage.setImageDrawable(d);
            ims.close();
        } catch (IOException ex) {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return iList.count();
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgChapter;
        private TextView tvName, tvDiscription,tvNumberLesson;

        public MViewHolder(final View itemView) {
            super(itemView);
            imgChapter = itemView.findViewById(R.id.img_chapter);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDiscription = itemView.findViewById(R.id.discription);
            tvNumberLesson=itemView.findViewById(R.id.tv_number_of_lesson);

            Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/montserrat_regular.otf");
            Typeface typeface2=Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/montserrat_light.otf");
            tvName.setTypeface(typeface);
            tvDiscription.setTypeface(typeface2);

        }
    }
}
