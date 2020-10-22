package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.ReadNewActivity;
import com.example.smartcityb_1.adapter.HomeNewsAdapter;
import com.example.smartcityb_1.bean.HomePhoto;
import com.example.smartcityb_1.bean.NewsList;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:39
 */
public class NewsFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private LinearLayout layoutNew;
    private ListView listView;
    private List<HomePhoto> homePhotos;
    private List<NewsList> newsLists;
    private AppHomeActivity appMainActivity;

    public NewsFragment(AppHomeActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public NewsFragment() {
    }

    public static NewsFragment newInstance(AppHomeActivity appHomeActivity) {
        return new NewsFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Image();
        setVolley_NewsList();
    }

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homePhotos = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<HomePhoto>>() {
                                }.getType());
                        setViewImage();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ImageView> imageViews;

    private void setViewImage() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < homePhotos.size(); i++) {
            VolleyImage volleyImage = new VolleyImage();
            final int finalI = i;
            volleyImage.setUrl(homePhotos.get(i).getPath())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            ImageView imageView = new ImageView(getActivity());
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), ReadNewActivity.class);
                                    intent.putExtra("id", homePhotos.get(finalI).getNewid());
                                    startActivity(intent);
                                }
                            });
                            if (imageViews.size() == 5) {
                                for (int i = 0; i < imageViews.size(); i++) {
                                    viewFlipper.addView(imageViews.get(i));
                                }
                                viewFlipper.startFlipping();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void setVolley_NewsList() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setVolley_NewsType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<String> newTypes;

    private void setVolley_NewsType() {
        newTypes = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllNewsType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Utils.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            newTypes.add(jsonObject1.optString("newstype"));
                        }
                        setVolley_Recommend();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<Integer> recommendNews;

    private void setVolley_Recommend() {
        recommendNews = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommend")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Utils.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            recommendNews.add(Integer.parseInt(jsonObject1.optString("newsid")));
                        }
                        setNewType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setNewType() {
        newTypes.add(0, "推荐");
        int j = 0;
        for (String string : newTypes) {
            final TextView textView = new TextView(getActivity());
            textView.setText(string);
            textView.setTextColor(Color.parseColor("#333333"));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            final int finalJ = j;
            textView.setBackgroundResource(R.drawable.text_no_line);
            if (finalJ == 0) {
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView.setBackgroundResource(R.drawable.text_line);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < layoutNew.getChildCount(); i++) {
                        TextView textView = (TextView) layoutNew.getChildAt(i);
                        if (i == finalJ) {
                            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                            textView.setBackgroundResource(R.drawable.text_line);
                        } else {
                            textView.setTextColor(Color.parseColor("#333333"));
                            textView.setBackgroundResource(R.drawable.text_no_line);
                        }
                    }
                    String type = textView.getText().toString();
                    setListView(type);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20, 0, 20, 0);
            textView.setLayoutParams(layoutParams);
            layoutNew.addView(textView);
            j++;
        }
        setListView("推荐");
    }

    private void setListView(String type) {
        final List<NewsList> newsListList = new ArrayList<>();
        for (int j = 0; j < newsLists.size(); j++) {
            NewsList newsList1 = newsLists.get(j);
            if (type.equals("推荐")) {
                if (recommendNews.contains(Integer.parseInt(newsList1.getNewsid()))) {
                    newsListList.add(newsList1);
                }
            } else {
                if (newsList1.getNewsType().equals(type)) {
                    newsListList.add(newsList1);
                }
            }
        }
        listView.setAdapter(new HomeNewsAdapter(getActivity(), newsListList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ReadNewActivity.class);
                intent.putExtra("info", newsListList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        layoutNew = getView().findViewById(R.id.layout_new);
        listView = getView().findViewById(R.id.list_view);
    }
}
