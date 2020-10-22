package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.CarInfo;
import com.example.smartcityb_1.util.OnUpDate2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 14:50
 */
public class HCarAdapter extends ArrayAdapter<CarInfo> {
    List<CarInfo> carInfos;
    public HCarAdapter(@NonNull Context context, @NonNull List<CarInfo> objects) {
        super(context, 0, objects);
        carInfos = objects;
    }

    @Override
    public int getCount() {
        return carInfos.size()+1;
    }

    private OnUpDate2 onUpDate2;

    public void setOnUpDate2(OnUpDate2 onUpDate2) {
        this.onUpDate2 = onUpDate2;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (position != getCount() - 1) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.h_car_item, parent, false);
                holder = new ViewHolder();
                holder.itemName = convertView.findViewById(R.id.item_name);
                holder.itemSex = convertView.findViewById(R.id.item_sex);
                holder.itemID = convertView.findViewById(R.id.item_ID);
                holder.itemTel = convertView.findViewById(R.id.item_tel);
                holder.itemAddress = convertView.findViewById(R.id.item_address);
                holder.itemLeft = convertView.findViewById(R.id.item_left);
                holder.itemLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onUpDate2.upDate("1", position);
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CarInfo carInfo = getItem(position);
            holder.itemName.setText("姓名：" + carInfo.getName());
            holder.itemSex.setText("性别：" + carInfo.getSex());
            holder.itemAddress.setText("地址：" + carInfo.getAddress());
            holder.itemTel.setText("手机号：" + carInfo.getTel());
            holder.itemID.setText("省份证号：" + carInfo.getID());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpDate2.upDate("3", position);
                }
            });
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.h_car_item_2, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpDate2.upDate("2", position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {

        private TextView itemName;
        private TextView itemSex;
        private TextView itemID;
        private TextView itemTel;
        private TextView itemAddress;
        private ImageView itemLeft;
    }

    private void initView() {
    }
}
