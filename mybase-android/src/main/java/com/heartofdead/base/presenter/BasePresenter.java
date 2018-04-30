package com.heartofdead.base.presenter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.heartofdead.base.interfaces.IBasePresenter;
import com.heartofdead.base.interfaces.MCallBack;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by nguyenxuanhoi2903 on 20/11/2017.
 */

public  class BasePresenter<V> implements IBasePresenter {
    private static final String TAG = BasePresenter.class.getSimpleName();
    private V mView;
    private List<Disposable> disposables;

    public V getMyView() {
        return mView;
    }

    public BasePresenter(V mView) {
        this.mView = mView;
        if(mView     == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        disposables = new ArrayList<>();
    }

    protected  <O> void interact(Observable<O> ob, MCallBack<O> onNext, MCallBack<Throwable> onError) {
        ob = ob.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        checkDisposable();
//        Disposable disposable = ob.subscribe(new Consumer<E>() {
//            @Override
//            public void accept(E e) throws Exception {
//                onNext.call(e);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                onError.call(throwable);
//            }
//        });

        //cu phap lamda
        //disposable kich hoat phuong thuc subscribe trong Observable
        Disposable disposable = ob.subscribe(response -> {
           //callback interface. dung interface de chuyen du lieu
            onNext.call(response);
        }, error -> {
            onError.call(error);
        });
        disposables.add(disposable);

    }

    @SuppressLint("NewApi")
    @Override
    public void onDestroy() {
        //giai phong' ex:thread.....
        disposables.forEach(item->item.dispose());
        Log.d(TAG, "onDestroy: " + "=======");


    }

    protected void checkDisposable() {
        for (int i = disposables.size()-1; i >= 0; i--) {
            if (disposables.get(i).isDisposed()) {
                disposables.remove(i);
            }
        }
    }

}
