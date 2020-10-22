package com.example.smartcityb_1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 16:19
 */
public class AppClient extends Application {
    private static RequestQueue request;
    public static SharedPreferences preferences;
    public static final String IsFirst = "isfirst";
    public static final String Ip = "ip";
    public static final String PORT = "port";
    public static String username = "abc";
    private static List<AppCompatActivity> appCompatActivities = new ArrayList<>();
    private static List<AppCompatActivity> appCompatActivities2 = new ArrayList<>();
    public static String name, tel, address, time;

    public static void add(AppCompatActivity appCompatActivity) {
        appCompatActivities.add(appCompatActivity);
    }

    public static void add2(AppCompatActivity appCompatActivity) {
        appCompatActivities2.add(appCompatActivity);
    }

    public static void finallAll2() {
        for (int i = 0; i < appCompatActivities2.size(); i++) {
            AppCompatActivity appCompatActivity = appCompatActivities2.get(i);
            if (appCompatActivity != null && !appCompatActivity.isFinishing()) {
                appCompatActivity.finish();
            }
        }
    }

    public static void finallAll() {
        for (int i = 0; i < appCompatActivities.size(); i++) {
            AppCompatActivity appCompatActivity = appCompatActivities.get(i);
            if (appCompatActivity != null && !appCompatActivity.isFinishing()) {
                appCompatActivity.finish();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        request = Volley.newRequestQueue(this);
        preferences = getSharedPreferences("aa", 0);
    }

    public static void add(ImageRequest imageRequest) {
        request.add(imageRequest);
    }

    public static void add(JsonObjectRequest jsonObjectRequest) {
        request.add(jsonObjectRequest);
    }
}

