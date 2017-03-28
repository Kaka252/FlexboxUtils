package com.zhouyou.flexbox;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27
 */
public class StringTagAdapter extends TagAdapter<StringTagView, String> {

    StringTagAdapter(Context context, List<String> data) {
        this(context, data, null);
    }

    StringTagAdapter(Context context, List<String> data, List<String> selectItems) {
        super(context, data, selectItems);
    }

    @Override
    protected StringTagView addTag(String item) {
        StringTagView tagView = new StringTagView(getContext());
        tagView.setItemDefaultDrawable(R.drawable.bg_flow_unselect);
        tagView.setItemSelectDrawable(R.drawable.bg_flow_selected);
        tagView.setItemDefaultTextColor(ContextCompat.getColor(getContext(), R.color.app_green));
        tagView.setItemSelectTextColor(Color.WHITE);
        tagView.setItem(item);
        List<String> list = getSelectItems();
        if (list != null && list.size() > 0) {
            for (String select : getSelectItems()) {
                if (TextUtils.isEmpty(select)) continue;
                if (TextUtils.equals(select, item)) {
                    tagView.setItemSelected(true);
                    break;
                }
            }
        }
        return tagView;
    }

    @Override
    protected List<String> getSelectedList() {
        List<String> selectedList = new ArrayList<>();
        for (StringTagView view : viewMap.keySet()) {
            if (view.isItemSelected()) {
                String item = viewMap.get(view);
                selectedList.add(item);
            }
        }
        return selectedList;
    }

    @Override
    protected void singleSelectMode(String item) {
        for (StringTagView view : viewMap.keySet()) {
            if (view.getItem().equals(item)) {
                view.setItemSelected(true);
            } else {
                view.setItemSelected(false);
            }
        }
    }
}
