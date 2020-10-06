package com.example.smartcityb_1.net;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.smartcityb_1.AppClient;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:32
 */
public class VolleyImage extends Thread {
    private String url;
    private VolleyLoImage volleyLoImage;

    public VolleyImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public VolleyImage setVolleyLoImage(VolleyLoImage volleyLoImage) {
        this.volleyLoImage = volleyLoImage;
        return this;
    }

    @Override
    public void run() {
        super.run();
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                volleyLoImage.onResponse(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyLoImage.onErrorResponse(volleyError);
            }
        });
        AppClient.add(request);
    }
}
