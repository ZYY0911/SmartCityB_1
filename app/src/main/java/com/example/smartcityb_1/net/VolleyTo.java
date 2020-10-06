package com.example.smartcityb_1.net;

import android.net.Uri;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.smartcityb_1.AppClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 16:12
 */
public class VolleyTo extends Thread {
    private String Url = "http://" + AppClient.preferences.getString(AppClient.Ip, "192.168.155.106") + ":" +
            AppClient.preferences.getString(AppClient.PORT, "8080") + "/mobileA/";
    private boolean isLoop;
    private int time;
    private JSONObject jsonObject = new JSONObject();
    private VolleyLo volleyLo;

    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    public VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public VolleyTo setTime(int time) {
        this.time = time;
        return this;
    }

    public VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                }
            });
            AppClient.add(jsonObjectRequest);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isLoop);
    }
}
