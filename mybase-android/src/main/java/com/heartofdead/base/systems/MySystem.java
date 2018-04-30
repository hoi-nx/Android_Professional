package com.heartofdead.base.systems;

import com.heartofdead.base.interfaces.MCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heart Of Dead on 9/21/2017.
 */

public class MySystem<T> {
    private static MySystem mySystem = new MySystem();

    private List<MCallBack<T>> listChangeNetWork;

    public List<MCallBack<T>> getListChangeNetWork() {
        return listChangeNetWork;
    }

    public void setListChangeNetWork(List<MCallBack<T>> listChangeNetWork) {
        this.listChangeNetWork = listChangeNetWork;
    }

    public MySystem() {
        listChangeNetWork = new ArrayList<>();
    }

    public static MySystem getIntance() {
        return mySystem;
    }

    public void makeChangeInternet(T t) {
        for (MCallBack action : listChangeNetWork) {
            action.call(t);
        }
    }

    public void register(MCallBack action) {
        listChangeNetWork.add(action);
    }

    public void unregister(MCallBack action) {
        listChangeNetWork.remove(action);
    }
}
