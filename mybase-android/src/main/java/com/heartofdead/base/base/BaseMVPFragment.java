package com.heartofdead.base.base;

import android.util.Log;

import com.heartofdead.base.base.BaseFragment;
import com.heartofdead.base.interfaces.IBasePresenter;


/**
 * Created by nguyenxuanhoi2903 on 21/11/2017.
 */

public abstract class BaseMVPFragment <F extends IBasePresenter> extends BaseFragment {
    private static final String TAG=BaseMVPFragment.class.getSimpleName();
    private F mPresenter;

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView:============ ");
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
        super.onDestroyView();
    }
}
