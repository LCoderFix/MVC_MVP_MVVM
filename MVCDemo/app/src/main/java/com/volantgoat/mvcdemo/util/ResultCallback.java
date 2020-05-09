package com.volantgoat.mvcdemo.util;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by dong on 2020/5/9
 */
public interface ResultCallback {
    void onError(Request request, Exception e);
    void onResponse(Response response);
}
