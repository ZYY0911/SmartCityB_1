package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.TrafficActivity;
import com.example.smartcityb_1.bean.ServiceInfo;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.util.OnClickItem;
import com.example.smartcityb_1.util.OnUpDate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:11
 */
public class AllServiceGirdViewAdapter extends ArrayAdapter<ServiceInfo> {
    public AllServiceGirdViewAdapter(@NonNull Context context, @NonNull List<ServiceInfo> objects) {
        super(context, 0, objects);
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
            holder = new ViewHolder();
            holder.itemPhoto = convertView.findViewById(R.id.item_photo);
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemLayout = convertView.findViewById(R.id.item_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ServiceInfo serviceInfo = getItem(position);
        holder.itemName.setText(serviceInfo.getServiceName());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(serviceInfo.getIcon())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.itemPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
//        if (serviceInfo.getServiceName().equals("地铁查询")) {
//            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    getContext().startActivity(new Intent(getContext(), TrafficActivity.class));
//                }
//            });
//        }else if (serviceInfo.getServiceName().equals("新闻中心")){
//
//        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(serviceInfo.getServiceid(), serviceInfo.getServiceName());
            }
        });
        return convertView;
    }

    static class ViewHolder {

        private ImageView itemPhoto;
        private TextView itemName;
        private LinearLayout itemLayout;
    }
}
