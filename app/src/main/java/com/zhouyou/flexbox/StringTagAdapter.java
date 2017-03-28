package com.zhouyou.flexbox;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

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

    /**
     * 检查item和所选item是否一样
     *
     * @param view
     * @param item
     * @return
     */
    @Override
    protected boolean checkIsItemSame(StringTagView view, String item) {
        return TextUtils.equals(view.getItem(), item);
    }

    /**
     * 检查item是否是空指针
     *
     * @return
     */
    @Override
    protected boolean checkIsItemNull(String item) {
        return TextUtils.isEmpty(item);
    }

    /**
     * 添加标签
     *
     * @param item
     * @return
     */
    @Override
    protected StringTagView addTag(String item) {
        StringTagView tagView = new StringTagView(getContext());
        tagView.setItemDefaultDrawable(R.drawable.bg_flow_unselect);
        tagView.setItemSelectDrawable(R.drawable.bg_flow_selected);
        tagView.setItemDefaultTextColor(ContextCompat.getColor(getContext(), R.color.app_green));
        tagView.setItemSelectTextColor(Color.WHITE);
        tagView.setItem(item);
        return tagView;
    }
}
