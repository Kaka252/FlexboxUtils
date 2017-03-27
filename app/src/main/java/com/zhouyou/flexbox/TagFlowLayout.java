package com.zhouyou.flexbox;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;

/**
 * 作者：ZhouYou
 * 日期：2017/3/25.
 */
public class TagFlowLayout extends FlexboxLayout {

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(TagAdapter adapter) {
        if (adapter == null) return;
        adapter.addTags(this);
    }
}
