package com.volantgoat.mvcdemo.util;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by dong on 2020/5/8
 */
public class OkHttpEngine {
    private static volatile OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private android.os.Handler mHandler;


    public static OkHttpEngine getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpEngine.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }

    public OkHttpEngine(Context context) {
        File sdcache = context.getExternalCacheDir(); //设置缓存地址
        int cacheSize = 10 * 1024 * 1024; //缓存大小
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    /**
     * 异步GET请求
     * @param url 请求URL地址
     * @param callback  回调接口，UI线程
     */
    public void getAsynHttp(String url, ResultCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }

    private void dealResult(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                sendFailedCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                sendSuccessCallback(response, callback);
            }

            private void sendFailedCallback(final Request request, final IOException e, final ResultCallback callback) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(callback!=null){
                            callback.onError(request,e);
                        }
                    }
                });
            }

            private void sendSuccessCallback(final Response response, final ResultCallback callback) {
                if(callback!=null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(response);
                        }
                    });
                }
            }
        });
    }

}

