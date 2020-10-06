package com.example.smartcityb_1.net;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:32
 */
public interface VolleyLoImage  {
    void onResponse(Bitmap bitmap);
    void onErrorResponse(VolleyError volleyError);
}
