package com.volantgoat.mvcdemo.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.volantgoat.mvcdemo.base.BaseController;
import com.volantgoat.mvcdemo.bean.ImageBean;
import com.volantgoat.mvcdemo.util.OkHttpEngine;
import com.volantgoat.mvcdemo.util.ResultCallback;

import java.io.InputStream;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by dong on 2020/5/9
 */
public class ImageDownController extends BaseController {
    private static volatile ImageDownController imageDownController;

    public static ImageDownController getInstance(){
        if(imageDownController==null){
            synchronized (ImageDownController.class){
                imageDownController=new ImageDownController();
            }
        }
        return imageDownController;
    }
    public void test(onImageDownListener listener){
        listener.callback(200,new ImageBean());
    }
    public void down(Context context, String url, final onImageDownListener listener){
        final ImageBean imageBean=new ImageBean();
        imageBean.setRequestPath(url);
        OkHttpEngine.getInstance(context).getAsynHttp(url, new ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                if(listener!=null){
                    listener.callback(400,imageBean);
                }
            }

            @Override
            public void onResponse(Response response) {
                InputStream in=response.body().byteStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);
                imageBean.setBitmap(bitmap);
                if(listener!=null){
                    listener.callback(200,imageBean);
                }
            }
        });
    }
}
