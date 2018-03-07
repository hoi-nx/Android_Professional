package com.mteam.android_professional.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.mteam.android_professional.Contants;
import com.mteam.android_professional.R;
import com.mteam.android_professional.obj.Lesson;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;

/**
 * Created by Stealer Of Souls on 2/8/2018.
 */

public class DetailActivity extends AppCompatActivity {
    private int idLesson;
    private MarkdownView mMarkdownView;
    private InternalStyleSheet mStyle = new Github();
    private int idChapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        initData();
        findViewByIds();
        initComponent();
    }

    private void initComponent() {


        mMarkdownView = (MarkdownView) findViewById(R.id.mark_view);
        mMarkdownView.addStyleSheet(mStyle);
        //http://stackoverflow.com/questions/6370690/media-queries-how-to-target-desktop-tablet-and-mobile
        mStyle.addRule("body", "line-height: 1.6", "padding: 10px","font-size: 16px");
        mStyle.addFontFace("MyFont", "condensed", "italic", "bold", "url('myfont.ttf')");
        mStyle.addRule("h1", "font-size: 30px");
        mStyle.addRule("h2", "font-size: 24px");
        mStyle.addRule("h3", "font-size: 18px");
        mStyle.addRule("h4", "font-size: 16px");
        mStyle.addRule("h5", "font-size: 14px");
        mStyle.addRule("h6", "font-size: 14px");
        mStyle.addMedia("screen and (min-width: 320px)");
        mStyle.addRule("h1", "color: green");
        mStyle.endMedia();
        mStyle.addMedia("screen and (min-width: 481px)");
        mStyle.addRule("h1", "color: red");
        mStyle.endMedia();
        mStyle.addMedia("screen and (min-width: 641px)");
        mStyle.addRule("h1", "color: blue");
        mStyle.endMedia();
        mStyle.addMedia("screen and (min-width: 961px)");
        mStyle.addRule("h1", "color: yellow");
        mStyle.endMedia();
        mStyle.addMedia("screen and (min-width: 1025px)");
        mStyle.addRule("h1", "color: gray");
        mStyle.endMedia();
        mStyle.addMedia("screen and (min-width: 1281px)");
        mStyle.addRule("h1", "color: orange");
        mStyle.endMedia();
       // mMarkdownView.setBean(myBean);
        mMarkdownView.loadMarkdownFromAsset("data/mds/c"+idChapter+"/bai"+idLesson+"_c"+idChapter+".md");

        mMarkdownView.getSettings().setBuiltInZoomControls(true);
        mMarkdownView.getSettings().setDisplayZoomControls(false);

    }

    private void findViewByIds() {
        mMarkdownView= (MarkdownView) findViewById(R.id.mark_view);

    }

    private void initData() {
        Intent intent=getIntent();
        idLesson=((Lesson)intent.getSerializableExtra(Contants.LESSON)).getIdLesson();
        idChapter=intent.getIntExtra(Contants.ID_CHAPTER,0);



    }
}
