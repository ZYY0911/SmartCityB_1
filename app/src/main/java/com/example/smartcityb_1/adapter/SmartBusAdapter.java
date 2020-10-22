package com.example.smartcityb_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.BusStation;
import com.example.smartcityb_1.bean.BusTitle;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 11:35
 */
public class SmartBusAdapter extends BaseExpandableListAdapter {
    private List<BusTitle> busTitles;
    private Map<BusTitle, List<BusStation>> map;

    public SmartBusAdapter(List<BusTitle> busTitles, Map<BusTitle, List<BusStation>> map) {
        this.busTitles = busTitles;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
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
        ViewHolderFu viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_fu, parent, false);
            viewHolder = new ViewHolderFu();
            viewHolder.itemName = convertView.findViewById(R.id.item_name);
            viewHolder.itemLine = convertView.findViewById(R.id.item_line);
            viewHolder.itemMsg = convertView.findViewById(R.id.item_msg);
            viewHolder.itemStart = convertView.findViewById(R.id.item_start);
            viewHolder.itemEnd = convertView.findViewById(R.id.item_end);
            viewHolder.itemIv = convertView.findViewById(R.id.item_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderFu) convertView.getTag();
        }
        BusTitle busTitle = busTitles.get(groupPosition);
        viewHolder.itemName.setText(busTitle.getPathName());
        viewHolder.itemLine.setText(busTitle.getStartSite() + "--" + busTitle.getEndSite());
        viewHolder.itemMsg.setText("票价：￥" + busTitle.getPrice() + ".0    里程：" + busTitle.getMileage() + ".0km");
        viewHolder.itemStart.setText(busTitle.getRunTime1());
        viewHolder.itemEnd.setText(busTitle.getRunTime2());
        if (isExpanded){
            viewHolder.itemIv.setImageResource(R.mipmap.xiajiantou);
        }else {
            viewHolder.itemIv.setImageResource(R.mipmap.youjiantou);
        }
        return convertView;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_zi, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        List<BusStation> busStations = map.get(busTitles.get(groupPosition));
        String str = "";
        for (int i = 0; i < busStations.size(); i++) {
            if (i == 0) {
                str = busStations.get(i).getSiteName();
            } else {
                str += "\r\n" + busStations.get(i).getSiteName();
            }
        }
        itemName.setText(str);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(groupPosition,"");
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolderFu {

        private TextView itemName;
        private TextView itemLine;
        private TextView itemMsg;
        private TextView itemStart;
        private TextView itemEnd;
        private ImageView itemIv;
    }

    private void initView() {
    }
}
