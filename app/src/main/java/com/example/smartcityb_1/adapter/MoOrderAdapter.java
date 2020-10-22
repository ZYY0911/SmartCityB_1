package com.example.smartcityb_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.MyOrder;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 15:59
 */
public class MoOrderAdapter extends BaseExpandableListAdapter {
    private List<MyOrder> myOrders;

    public MoOrderAdapter(List<MyOrder> myOrders) {

        this.myOrders = myOrders;
    }

    @Override
    public int getGroupCount() {
        return myOrders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolderFu viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_fu, parent, false);
            viewHolder = new ViewHolderFu();
            viewHolder.itemNum = convertView.findViewById(R.id.item_num);
            viewHolder.itemName = convertView.findViewById(R.id.item_name);
            viewHolder.itemLine = convertView.findViewById(R.id.item_line);
            viewHolder.itemMsg = convertView.findViewById(R.id.item_msg);
            viewHolder.itemIv = convertView.findViewById(R.id.item_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderFu) convertView.getTag();
        }
        MyOrder myOrder = myOrders.get(groupPosition);
        viewHolder.itemNum.setText("订单编号：" + myOrder.getId());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busListById")
                .setJsonObject("busid", myOrder.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonObject1 = jsonObject.optJSONArray(Utils.Rows).optJSONObject(0);
                        viewHolder.itemName.setText(jsonObject1.optString("pathName"));
                        viewHolder.itemLine.setText(jsonObject1.optString("startSite") + "-" + jsonObject1.optString("endSite"));
                        viewHolder.itemMsg.setText("票价：" + jsonObject1.optString("price"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        if (isExpanded) {
            viewHolder.itemIv.setImageResource(R.mipmap.xiajiantou);
        } else {
            viewHolder.itemIv.setImageResource(R.mipmap.youjiantou);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_zi, parent, false);

        TextView itemName = convertView.findViewById(R.id.item_name);
        MyOrder myOrder = myOrders.get(groupPosition);
        itemName.setText(myOrder.getTravetime().replace(",", "\r\n"));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolderFu {

        private TextView itemNum;
        private TextView itemName;
        private TextView itemLine;
        private TextView itemMsg;
        private ImageView itemIv;
    }

    private void initView() {
    }
}
