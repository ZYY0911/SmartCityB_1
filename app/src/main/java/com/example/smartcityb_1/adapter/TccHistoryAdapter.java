package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.Tcchistory;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 9:35
 */
public class TccHistoryAdapter extends ArrayAdapter<Tcchistory> {

    private int count;

    public TccHistoryAdapter(@NonNull Context context, @NonNull List<Tcchistory> objects, int count) {
        super(context, 0, objects);
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tcc_history_item, parent, false);
            holder = new ViewHolder();
            holder.itemCp = convertView.findViewById(R.id.item_cp);
            holder.itemJe = convertView.findViewById(R.id.item_je);
            holder.itemIn = convertView.findViewById(R.id.item_in);
            holder.itemOut = convertView.findViewById(R.id.item_out);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Tcchistory tcchistory = getItem(position);
        holder.itemCp.setText(tcchistory.getCarNum());
        holder.itemJe.setText(tcchistory.getCharge());
        holder.itemIn.setText(tcchistory.getInTime());
        holder.itemOut.setText(tcchistory.getOutTime());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getParkInforById")
                .setJsonObject("parkingid", tcchistory.getParkingid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        holder.itemName.setText(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).optString("parkName"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


        return convertView;
    }


    static class ViewHolder {

        private TextView itemCp;
        private TextView itemJe;
        private TextView itemIn;
        private TextView itemOut;
        private TextView itemName;
    }

    private void initView() {
    }
}
