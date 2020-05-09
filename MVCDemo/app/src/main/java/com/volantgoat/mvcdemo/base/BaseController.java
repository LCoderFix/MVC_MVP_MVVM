package com.volantgoat.mvcdemo.base;

import com.volantgoat.mvcdemo.bean.ImageBean;

/**
 * Create by dong on 2020/5/9
 */

public class BaseController {
    /**
     * 图像回传接口
     */
    public interface onImageDownListener{
        void callback(int resultCode, ImageBean imageBean);
    }
}
