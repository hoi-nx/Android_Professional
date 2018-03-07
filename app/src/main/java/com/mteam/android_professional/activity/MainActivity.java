package com.mteam.android_professional.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.mteam.android_professional.Contants;
import com.mteam.android_professional.fragments.FragmentChapter;
import com.mteam.android_professional.fragments.FragmentLesson;
import com.mteam.android_professional.R;
import com.mteam.android_professional.manager.ManagerDataSQLite;
import com.mteam.android_professional.obj.Chapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FragmentChapter mFragmentChapter;
    private KenBurnsView mKenBurnsView;

//    AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
//    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
//
//    private void animationOpenFragment(View h1, View h2) {
//        final View visibleList;
//        final View invisibleList;
//        if (h1.getVisibility() == View.GONE) {
//            visibleList = h2;
//            invisibleList = h1;
//        } else {
//            invisibleList = h2;
//            visibleList = h1;
//        }
//        h1.setVisibility(View.GONE);
//        ObjectAnimator objectAnimatorVisible = ObjectAnimator.ofFloat(visibleList, "rotationY", 0f, 90f);
//        objectAnimatorVisible.setDuration(500);
//        objectAnimatorVisible.setInterpolator(accelerateInterpolator);
//
//        final ObjectAnimator objectAnimatorInvisible = ObjectAnimator.ofFloat(invisibleList, "rotationY", -90f, 0f);
//        objectAnimatorInvisible.setDuration(500);
//        objectAnimatorInvisible.setInterpolator(decelerateInterpolator);
//        objectAnimatorVisible.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//             visibleList.setVisibility(View.GONE);
//                objectAnimatorInvisible.start();
//             invisibleList.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        objectAnimatorVisible.start();
//        h1.setVisibility(View.VISIBLE);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mKenBurnsView = (KenBurnsView) findViewById(R.id.mKenburns);
        initData();
        initComponent();


    }

    private void initComponent() {
       // randomBackground();

        FragmentManager manager = getFragmentManager();
        mFragmentChapter = new FragmentChapter();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_content, mFragmentChapter, FragmentChapter.class.getName());
        //transaction.addToBackStack(FragmentChapter.class.getName());
        transaction.commit();
    }

    private void randomBackground() {
        Random random = new Random();
        int index = random.nextInt(2);
        switch (index) {
            case 0:
              //  mKenBurnsView.setImageResource(R.drawable.ic_action_background_five);
                break;
            case 1:
             //   mKenBurnsView.setImageResource(R.drawable.ic_action_background_two);
                break;
            default:
                break;
        }
    }

    private void initData() {
        //ManagerDataSQLite.getIntance(this).inits(this);
        // ManagerDataSQLite.getIntance(this).onUpgrade(sqLiteDatabase,1,2);

    }

    @SuppressLint("ResourceType")
    public void openFragmentLesson(Chapter chapter) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Contants.CHAPTER, chapter);
        FragmentManager manager = getFragmentManager();
        //kiem tra fragment login da ton tai trong fragmentmanager chua
        FragmentLesson fragment =
                (FragmentLesson) manager.findFragmentByTag(FragmentLesson.class.getName());
        //if fragment == null thi fragment chua co trong fragmentmanager
        if (fragment != null) {
            if (fragment.isVisible()) {

            } else {
                FragmentTransaction transaction = manager.beginTransaction();
//                transaction .setCustomAnimations(
//                        R.animator.card_flip_right_in,
//                        R.animator.card_flip_right_out,
//                        R.animator.card_flip_left_in,
//                        R.animator.card_flip_left_out);
                transaction.replace(R.id.frame_content, fragment, FragmentLesson.class.getName());
                transaction.addToBackStack(FragmentLesson.class.getName());

                transaction.commit();
            }
            return;
        }

        //tao ra login fragment va them vao
        fragment = new FragmentLesson();
        fragment.setArguments(bundle);
        manager.beginTransaction().
                setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out).replace(R.id.frame_content, fragment, FragmentLesson.class.getName()).addToBackStack(FragmentLesson.class.getName()).commit();
    }


}
