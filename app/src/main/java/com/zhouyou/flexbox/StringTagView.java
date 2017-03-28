package com.zhouyou.flexbox;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import zhouyou.flexbox.widget.BaseTagView;

/**
 * 作者：ZhouYou
 * 日期：2017/3/25.
 */
public class StringTagView extends BaseTagView<String> {

    public StringTagView(Context context) {
        this(context, null);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setItem(String item) {
        super.setItem(item);
        textView.setText(item);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Toast.makeText(getContext(), getItem(), Toast.LENGTH_SHORT).show();
    }
}
