package com.heartofdead.base.api;

import android.content.Context;

/**
 * Created by Heart Of Dead on 28/12/2017.
 */

public interface IRetrofit {
    void attachBaseUrl(Context context, String baseUrl);

    <T> T createService(Class<T> mClass);

    void destory();

}
