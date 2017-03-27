package com.zhouyou.flexbox;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27
 */
public class StringTagAdapter extends TagAdapter<StringTagView, String> {

    StringTagAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected StringTagView addTag(String item) {
        StringTagView tagView = new StringTagView(getContext());
        tagView.setItem(item);
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
