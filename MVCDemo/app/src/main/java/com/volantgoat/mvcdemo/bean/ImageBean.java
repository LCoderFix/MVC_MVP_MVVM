package com.volantgoat.mvcdemo.bean;

import android.graphics.Bitmap;

/**
 * Create by dong on 2020/5/9
 */
public class ImageBean {
    private String requestPath;//网络图片地址
    private Bitmap bitmap;//结果返回Bitmap对象

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
