package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.ServiceInfo;
import com.example.smartcityb_1.util.OnClickItem;
import com.example.smartcityb_1.util.OnUpDate;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:06
 */
public class AllServiceAdapter extends BaseExpandableListAdapter {
    private List<String> type;
    private Map<String, List<ServiceInfo>> map;
    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public AllServiceAdapter(List<String> type, Map<String, List<ServiceInfo>> map) {
        this.type = type;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return type.size();
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
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_fu, parent, false);
            holderFu = new ViewHolderFu();
            holderFu.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        holderFu.itemName.setText(type.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_zi, parent, false);
            holderZi = new ViewHolderZi();
            holderZi.girdView = convertView.findViewById(R.id.gird_view);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        int count = map.get(type.get(groupPosition)).size();
        int line = count / 4;
        if (count % 4 != 0) {
            line++;
        }
        Log.i("bbb", "getChildView: " + count + "----" + line);
        ViewGroup.LayoutParams params = holderZi.girdView.getLayoutParams();
        params.height = px2dip(parent.getContext(), 70) * line;
        AllServiceGirdViewAdapter allServiceGirdViewAdapter = new AllServiceGirdViewAdapter(
                parent.getContext(), map.get(type.get(groupPosition)));
//        allServiceGirdViewAdapter.setOnUpDate(new OnUpDate() {
//                    @Override
//                    public void upDate(String name) {
//                        onUpDate.upDate(name);
//            }
//        });
        allServiceGirdViewAdapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
               onClickItem.onClick(position,name);
            }
        });

        holderZi.girdView.setAdapter(allServiceGirdViewAdapter);
        return convertView;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scal = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue * scal + 0.5f);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ViewHolderFu {
        private TextView itemName;
    }

    static class ViewHolderZi {
        private GridView girdView;
    }

}
