package com.example.smartcityb_1.adapter;

import android.content.Context;
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
import com.example.smartcityb_1.bean.HomeService;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.util.OnUpDate2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:44
 */
public class HomeServiceAdapter extends ArrayAdapter<HomeService> {

    public HomeServiceAdapter(@NonNull Context context, @NonNull List<HomeService> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return 10;
    }

    private OnUpDate2 onUpDate;

    public void setOnUpDate(OnUpDate2 onUpDate) {
        this.onUpDate = onUpDate;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
            holder = new ViewHolder();
            holder.itemLayout = convertView.findViewById(R.id.item_layout);
            holder.itemPhoto = convertView.findViewById(R.id.item_photo);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==9){
            holder.itemName.setText("更多服务");
            holder.itemPhoto.setImageResource(R.mipmap.more_service);
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpDate.upDate("更多服务",position);
                }
            });
        }else {
            final HomeService homeService = getItem(position);
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(homeService.getImage())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemPhoto.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            holder.itemName.setText(homeService.getName());
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpDate.upDate(homeService.getName(), position);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {

        private LinearLayout itemLayout;
        private ImageView itemPhoto;
        private TextView itemName;
    }

    private void initView() {
    }
}
