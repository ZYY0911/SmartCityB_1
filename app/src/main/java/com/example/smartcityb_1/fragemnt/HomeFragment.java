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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AllServiceUrl;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.HdActivity;
import com.example.smartcityb_1.activity.HouTheam;
import com.example.smartcityb_1.activity.ReadNewActivity;
import com.example.smartcityb_1.activity.TrafficActivity;
import com.example.smartcityb_1.adapter.HomeServiceAdapter;
import com.example.smartcityb_1.adapter.HoutThemeAdapter;
import com.example.smartcityb_1.bean.HomeImages;
import com.example.smartcityb_1.bean.HomeService;
import com.example.smartcityb_1.bean.NewsList;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnUpDate2;
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
 * @Create by 张瀛煜 on 2020/10/5 at 17:26
 */
public class HomeFragment extends Fragment {
    private AppHomeActivity appHomeActivity;
    private ViewFlipper viewFlipper;
    private GridView girdService;
    private GridView girdTheme;
    private LinearLayout layoutNew;
    private LinearLayout newsLayout;
    List<HomeImages> homeImages;
    List<HomeService> homeServices;

    public HomeFragment() {
    }

    public HomeFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }


    public static HomeFragment newInstance(AppHomeActivity appHomeActivity) {
        return new HomeFragment(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragemnt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Images();
        setVolley_Service();
        setVolley_Subject();
        setVolley_NewType();
    }

    List<NewsList> newsLists;

    private void setVolley_NewType() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setVolley_Recommend();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<Integer> integers;

    private void setVolley_Recommend() {
        integers = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommend")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Utils.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            integers.add(Integer.parseInt(jsonObject1.optString("newsid")));
                        }
                        setLayoutType();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    Map<String, String> map;

    private void setLayoutType() {
        map = new HashMap<>();
        NewsList newsList = new NewsList();
        newsList.setNewsType("推荐");
        newsList.setNewsid("-9");
        newsLists.add(0, newsList);
        for (int i = 0; i < newsLists.size(); i++) {
            map.put(newsLists.get(i).getNewsType(), newsLists.get(i).getNewsType());
        }
        int j = 0;
        for (String string : map.values()) {
            final TextView textView = new TextView(appHomeActivity);
            textView.setText(string);
            textView.setTextColor(Color.parseColor("#333333"));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.text_no_line);
            if (j == 0) {
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView.setBackgroundResource(R.drawable.text_line);
            }
            final int finalJ = j;
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
                    setLayoutType(type);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 0, 40, 0);
            textView.setLayoutParams(layoutParams);
            layoutNew.addView(textView);
            j++;
        }
        setLayoutType("推荐");

    }

    private void setLayoutType(String type) {
        List<NewsList> newsLists1 = new ArrayList<>();
        for (int i = 0; i < newsLists.size(); i++) {
            NewsList newsList = newsLists.get(i);
            if (type.equals("推荐")) {
                if (isTyj(Integer.parseInt(newsList.getNewsid()))) {
                    newsLists1.add(newsList);
                }
            } else {
                if (newsList.getNewsType().equals(type)) {
                    newsLists1.add(newsList);
                }
            }
        }
        newsLayout.removeAllViews();

        for (int j = 0; j < newsLists1.size(); j++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(10, 5, 10, 5);
            view.setLayoutParams(layoutParams);
            final ViewHolder holder = new ViewHolder();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemTitle = view.findViewById(R.id.item_title);
            holder.itemContext = view.findViewById(R.id.item_context);
            holder.itemMsg = view.findViewById(R.id.item_msg);
            final NewsList newsList = newsLists1.get(j);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ReadNewActivity.class);
                    intent.putExtra("info", newsList);
                    startActivity(intent);
                }
            });
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(newsList.getPicture())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            holder.itemTitle.setText(newsList.getTitle());
            holder.itemContext.setText(newsList.getAbstractX());
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getCommitById")
                    .setJsonObject("id", newsList.getNewsid())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            JSONArray jsonArray = jsonObject.optJSONArray(Utils.Rows);
                            final int all = jsonArray.length();
                            VolleyTo volleyTo1 = new VolleyTo();
                            volleyTo1.setUrl("getNewsInfoById")
                                    .setJsonObject("newsid", newsList.getNewsid())
                                    .setVolleyLo(new VolleyLo() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            holder.itemMsg.setText("总评：" + all + "发布日期：" + jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).optString("publicTime"));

                                        }

                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                        }
                                    }).start();

                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();

            newsLayout.addView(view);
        }

    }


    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }

    private boolean isTyj(int id) {
        for (int i = 0; i < integers.size(); i++) {
            if (id == integers.get(i)) {
                return true;
            }
        }
        return false;
    }

    List<String> strings;

    private void setVolley_Subject() {
        strings = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubject")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String jsonArray[] = jsonObject.optString("ROWS_DETAIL").replace("[", "").replace("]", "").split(",");
                        for (int i = 0; i < jsonArray.length; i++) {
                            strings.add(jsonArray[i].trim());
                        }
                        girdTheme.setAdapter(new HoutThemeAdapter(getActivity(), strings));
                        girdTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), HouTheam.class);
                                intent.putExtra("info", "热门主题");
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", 2)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homeServices = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<HomeService>>() {
                                }.getType());
                        HomeServiceAdapter adapter = new HomeServiceAdapter(getActivity(), homeServices);
                        girdService.setAdapter(adapter);
                        adapter.setOnUpDate(new OnUpDate2() {
                            @Override
                            public void upDate(String name, int index) {
                                if (name.equals("活动")) {
                                    startActivity(new Intent(appHomeActivity, HdActivity.class));
                                } else if (name.equals("违章查询")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(24)).commit();
                                } else if (name.equals("门诊预约")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(23)).commit();
                                } else if (name.equals("更多服务")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(25)).commit();
                                } else if (name.equals("城市巴士")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(3)).commit();
                                } else if (name.equals("停车场")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(1)).commit();
                                } else if (name.equals("生活缴费")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(5)).commit();
                                } else if (name.equals("地铁查询")) {
                                    startActivity(new Intent(getActivity(), TrafficActivity.class));
                                } else if (name.equals("新闻中心")) {
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(26)).commit();
                                } else {
                                    Intent intent = new Intent(appHomeActivity, AllServiceUrl.class);
                                    intent.putExtra("infos", homeServices.get(index).getId());
                                    intent.putExtra("date", name);
                                    startActivity(intent);
                                }

                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Images() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homeImages = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<HomeImages>>() {
                                }.getType());
                        setViewFlipper();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ImageView> imageViews;

    private void setViewFlipper() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < homeImages.size(); i++) {
            final HomeImages homeImage = homeImages.get(i);
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(homeImage.getPath())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            ImageView imageView = new ImageView(getActivity());
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            if (imageViews.size() == 5) {
                                for (int j = 0; j < imageViews.size(); j++) {
                                    viewFlipper.addView(imageViews.get(j));
                                }
                                viewFlipper.startFlipping();
                            }
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), ReadNewActivity.class);
                                    intent.putExtra("id", homeImage.getNewid());
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        girdService = getView().findViewById(R.id.gird_service);
        girdTheme = getView().findViewById(R.id.gird_theme);
        layoutNew = getView().findViewById(R.id.layout_new);
        newsLayout = getView().findViewById(R.id.news_layout);
    }
}
