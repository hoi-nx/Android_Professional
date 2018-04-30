package com.heartofdead.base.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heartofdead.base.animation.ScreenAnimation;
import com.heartofdead.base.interfaces.IViewMain;

import java.util.List;


/**
 * Created by nguyenxuanhoi2903 on 20/11/2017.
 */

public abstract class BaseFragment<T> extends Fragment implements IViewMain {

    private T mObject;
    public void setList(T mObject) {
        this.mObject = mObject;
    }

    public T getList() {
        return mObject;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutMain(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewByIds();
        initComponents();
        setEvents();
    }


    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Class<? extends BaseFragment> newClass,
                                    ScreenAnimation screenAnimation,
                                    Bundle data,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                fragment.setArguments(data);
                transaction.add(containerViewId, fragment, tag);


            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Class<? extends BaseFragment> newClass,
                                    ScreenAnimation screenAnimation,
                                    Bundle data,
                                    int containerViewId,
                                    boolean isCommit) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                fragment.setArguments(data);
                transaction.add(containerViewId, fragment, tag);


            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (manager.getBackStackEntryCount() == 0) {
            transaction.addToBackStack(null);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    BaseFragment fragment,
                                    ScreenAnimation screenAnimation,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = fragment.getClass().getName();
        transaction.setCustomAnimations(
                screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
        transaction.add(containerViewId, fragment, tag);
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void openFragment(FragmentManager manager,
                                    FragmentTransaction transaction,
                                    Fragment fragment,
                                    ScreenAnimation screenAnimation,
                                    int containerViewId,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = fragment.getClass().getName();
        transaction.setCustomAnimations(
                screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
        transaction.add(containerViewId, fragment, tag);
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public static void hideFragment(FragmentManager manager, FragmentTransaction transaction,
                                    Class<? extends BaseFragment> classHide,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        String tag = classHide.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment != null && fragment.isVisible()) {
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.hide(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(tag);
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static void hideFragment(FragmentTransaction transaction,
                                    BaseFragment fragment,
                                    ScreenAnimation screenAnimation,
                                    boolean isAddBackStack,
                                    boolean isCommit) {
        if (fragment != null && fragment.isVisible()) {
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.hide(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(fragment.getClass().getName());
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static void removeFragment(FragmentManager manager,
                                      FragmentTransaction transaction,
                                      String tagFragment,
                                      ScreenAnimation screenAnimation,
                                      boolean isAddBackStack,
                                      boolean isCommit) {
        Fragment fragment = manager.findFragmentByTag(tagFragment);
        if (fragment != null) {
            if (screenAnimation != null) {
                transaction.setCustomAnimations(
                        screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                        screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            }
            transaction.remove(fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(tagFragment);
            }
            if (isCommit) {
                transaction.commit();
            }
        }
    }

    public static BaseFragment getCurrentBaseFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return null;
        }
        for (int i = fragments.size() - 1; i >= 0; i--) {
            BaseFragment fragment = (BaseFragment) fragments.get(i);
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;

    }

    public static Fragment getCurrentFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments == null) {
            return null;
        }
        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment fragment = fragments.get(i);
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;

    }

    public void removeCurrentFragment(String tag) {
        Fragment fragment = BaseFragment.getCurrentBaseFragment(getChildFragmentManager());
        if (fragment != null && fragment.getTag() != tag) {
            BaseFragment.removeFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), fragment.getTag(), ScreenAnimation.NONE, false, true);
        }
    }

    public void hildeCurrentFragment(String tag) {
        Fragment fragment = BaseFragment.getCurrentBaseFragment(getChildFragmentManager());
        if (fragment != null && fragment.getTag() != tag) {
            BaseFragment.removeFragment(getChildFragmentManager(), getChildFragmentManager().beginTransaction(), fragment.getTag(), ScreenAnimation.NONE, false, true);
        }
    }

    public static <T> void openFragmentSendObject(FragmentManager manager,
                                                  FragmentTransaction transaction,
                                                  Class<? extends BaseFragment> newClass,
                                                  ScreenAnimation screenAnimation,
                                                  int containerViewId,
                                                  boolean isAddBackStack,
                                                  boolean isCommit, T t) {
        String tag = newClass.getName();
        BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = newClass.newInstance();
                fragment.setList(t);
                transaction.setCustomAnimations(screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(), screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
                transaction.add(containerViewId, fragment, tag);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isVisible()) {
                return;
            }
            transaction.setCustomAnimations(
                    screenAnimation.getEnterToRight(), screenAnimation.getExitToRight(),
                    screenAnimation.getEnterToLeft(), screenAnimation.getExitToLeft());
            transaction.show(fragment);
        }
        if (isAddBackStack) {
            transaction.addToBackStack(tag);
        }
        if (isCommit) {
            transaction.commit();
        }
    }

    public void nextActivity(Class<?> mClass) {
        Intent intent = new Intent(getActivity(), mClass);
        startActivity(intent);
    }

    protected void nextActivityAndKill(Class<?> mClass) {
        nextActivity(mClass);
        getActivity().finish();
    }

    protected void nextActivityForResult(Class<?> mClass, int requestCode) {
        Intent intent = new Intent(getActivity(), mClass);
        startActivityForResult(intent, requestCode);
    }


}
