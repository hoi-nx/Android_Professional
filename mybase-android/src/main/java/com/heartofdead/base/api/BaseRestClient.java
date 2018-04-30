package com.heartofdead.base.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Heart Of Dead on 28/12/2017.
 */

public class BaseRestClient implements IRetrofit {
    private Retrofit mRetrofit;

    @Override
    public void attachBaseUrl(Context context, String baseUrl) {
        OkHttpClient.Builder http =
                new OkHttpClient.Builder()
                        .readTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(100, TimeUnit.SECONDS)
                        .connectTimeout(100, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(http.build())
                .build();

    }

    @Override
    public <T> T createService(Class<T> mClass) {
        return mRetrofit.create(mClass);
    }

    @Override
    public void destory() {
        mRetrofit = null;
    }
}
