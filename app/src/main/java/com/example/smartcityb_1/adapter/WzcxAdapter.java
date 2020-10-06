package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.CarPail;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 9:11
 */
public class WzcxAdapter extends ArrayAdapter<CarPail> {


    private int count;

    public WzcxAdapter(@NonNull Context context, @NonNull List<CarPail> objects, int count) {
        super(context, 0, objects);
        this.count = count;
    }

    Random random = new Random();

    @Override
    public int getCount() {
//        return getMyCunt();
        return count;
    }

    public void getMyCunt(int count) {
        this.count = count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wzcx_item, parent, false);
            holder = new ViewHolder();
            holder.itemCp = convertView.findViewById(R.id.item_cp);
            holder.itemZt = convertView.findViewById(R.id.item_zt);
            holder.itemSj = convertView.findViewById(R.id.item_sj);
            holder.itemJe = convertView.findViewById(R.id.item_je);
            holder.itemKf = convertView.findViewById(R.id.item_kf);
            holder.itemDd = convertView.findViewById(R.id.item_dd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CarPail carPail = getItem(position);
        holder.itemCp.setText("车牌号：" + carPail.getCarid());
        holder.itemJe.setText("罚款金额：" + carPail.getCost() + "元");
        holder.itemKf.setText("违章记分：" + carPail.getDeductPoints() + "分");
        holder.itemZt.setText("处理状态" + (carPail.getStatus() == 0 ? "未处理" : "处理中"));
        holder.itemSj.setText("违章时间：" + carPail.getTime());
        holder.itemDd.setText("违章地点：" + carPail.getPlace());
        return convertView;
    }

    static class ViewHolder {

        private TextView itemCp;
        private TextView itemZt;
        private TextView itemSj;
        private TextView itemJe;
        private TextView itemKf;
        private TextView itemDd;
    }

    private void initView() {
    }
}
