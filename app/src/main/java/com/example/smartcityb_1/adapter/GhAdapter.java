package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.DutByDempart;
import com.example.smartcityb_1.util.OnUpDate2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 15:48
 */
public class GhAdapter extends ArrayAdapter<DutByDempart> {

    String ks;

    public GhAdapter(@NonNull Context context, @NonNull List<DutByDempart> objects, String ks) {
        super(context, 0, objects);
        this.ks = ks;
    }

    private OnUpDate2 onUpDate2;

    public void setOnUpDate2(OnUpDate2 onUpDate2) {
        this.onUpDate2 = onUpDate2;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.gh_item, parent, false);
        TextView itemMsg = convertView.findViewById(R.id.item_msg);
        Button itemBt = convertView.findViewById(R.id.item_bt);
        DutByDempart dutByDempart = getItem(position);
        // {
        //            "num": 1,
        //            "hospitalId": "1",
        //            "departmentId": "1",
        //            "time": "2020-10-1 周四 下午14：00",
        //            "type": "普通门诊",
        //            "doctorId": "1"
        //        }
        itemMsg.setText(dutByDempart.getTime() + "," + ks);
        itemBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpDate2.upDate("", position);
            }
        });
        return convertView;
    }

    private void initView() {


    }
}
