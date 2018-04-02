package com.mteam.android_professional.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mteam.android_professional.R;
import com.mteam.android_professional.Utils;
import com.mteam.android_professional.activity.MainActivity;
import com.mteam.android_professional.adapter.AdapterRcChapter;
import com.mteam.android_professional.interfaces.IList;
import com.mteam.android_professional.obj.Chapter;
import com.stone.vega.library.VegaLayoutManager;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

/**
 * Created by Stealer Of Souls on 2/17/2018.
 */

public class FragmentChapter extends Fragment implements IList {
    private AdapterRcChapter adapterChapter;
    private RecyclerView rcView;
    private List<Chapter> chapterList;
    private ImageView imgMenu;
    private TextView tvNameChapter;
    private Typeface mRegular;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chapter,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcView =getView(). findViewById(R.id.rc_chapter);
        imgMenu=getView().findViewById(R.id.img_menu);
        rcView.setLayoutManager(new VegaLayoutManager());
        tvNameChapter=getView().findViewById(R.id.tv_name_chapter);
        initData();
        runAnimation(rcView);
        mRegular= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Anton-Regular.ttf");
        tvNameChapter.setTypeface(mRegular);



    }
    private void initData() {
      getActivity().runOnUiThread(new Runnable() {
          @Override
          public void run() {
               String jsonString= Utils.readFileFromAssets("data/chapter_android.json",getActivity());
              Log.d("", "initData: "+jsonString);
              Gson gson = new Gson();
              Type type = new TypeToken<List<Chapter>>(){}.getType();
              chapterList = gson.fromJson(jsonString, type);
          }
      });

      imgMenu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ((MainActivity)getActivity()).openMenu();
          }
      });



        




    }
    private void runAnimation(RecyclerView rc) {
        Context context = rc.getContext();
        LayoutAnimationController controller = null;
        int i = new Random().nextInt(3);
        if (i == 0) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
        } else if (i == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_bottom);
        } else if (i == 2) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_right);
        }

        adapterChapter = new AdapterRcChapter(this);
        rc.setAdapter(adapterChapter);
        rc.setLayoutAnimation(controller);
        rc.getAdapter().notifyDataSetChanged();
        rc.scheduleLayoutAnimation();
    }

    @Override
    public int count() {
        return chapterList == null ? 0 : chapterList.size();
    }

    @Override
    public Object getData(int position) {
        return chapterList.get(position);
    }

    @Override
    public void itemClick(int position) {
        Chapter chapter = chapterList.get(position);
        if(chapter.getIdChapter()>6 && chapter.getIdChapter()!=30){
            Toast.makeText(getActivity(),"Bài học sẽ được cập nhập ở version tiếp theo",Toast.LENGTH_LONG).show();
            return;
        }
       ((MainActivity)getActivity()).openFragmentLesson(chapter);
    }

    @Override
    public int countLesson(int possion) {
        return Utils.getLessonList(chapterList.get(possion),getActivity())==null?0: Utils.getLessonList(chapterList.get(possion),getActivity()).size();
    }

}
