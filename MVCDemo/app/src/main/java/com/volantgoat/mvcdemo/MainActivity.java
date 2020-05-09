package com.volantgoat.mvcdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.volantgoat.mvcdemo.base.BaseController;
import com.volantgoat.mvcdemo.bean.ImageBean;
import com.volantgoat.mvcdemo.controller.ImageDownController;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    ImageDownController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        controller.down(MainActivity.this, "https://timgsa.baidu.com/timg?image&quality=" +
                "80&size=b9999_10000&sec=1589016359643&di=c" +
                "64f9b2deb6a0b9b84501bf434225e9d&imgtype" +
                "=0&src=http%3A%2F%2Fimg.ewebweb.com%2" +
                "Fuploads%2F20190623%2F22%2F1561299496-KISsoENQuM.jpg", new BaseController.onImageDownListener() {
            @Override
            public void callback(int resultCode, ImageBean imageBean) {
                if(resultCode==200){
                    image.setImageBitmap(imageBean.getBitmap());
                }
            }
        });
    }
    public void init(){
        image=findViewById(R.id.image_view);
        controller=ImageDownController.getInstance();
    }
}
