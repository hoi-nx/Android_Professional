package com.heartofdead.base.interfaces;

/**
 * Created by nguyenxuanhoi2903 on 02/12/2017.
 */

public interface IBaseViewMain<T> {
    void finishGetData(T listV);
    void errorGetData(Throwable throwable);
}
