package com.zhouyou.flexbox;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 作者：ZhouYou
 * 日期：2017/3/25.
 */
public class BaseTagView<T> extends FrameLayout implements View.OnClickListener {

    private Context context;

    private T item;

    public TextView textView;

    private TagWithListener<T> listener;

    private boolean isItemSelected;

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
        if (itemSelected) {
            setBackgroundResource(R.drawable.bg_flow_selected);
            textView.setTextColor(Color.WHITE);
        } else {
            setBackgroundResource(R.drawable.bg_flow_unselect);
            textView.setTextColor(ContextCompat.getColor(context, R.color.app_green));
        }
    }

    public void setListener(TagWithListener<T> listener) {
        this.listener = listener;
    }

    public BaseTagView(Context context) {
        this(context, null);
    }

    public BaseTagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        setPadding(15, 15, 15, 15);
        setBackgroundResource(R.drawable.bg_flow_unselect);
        textView = new TextView(getContext());
        textView.setTextColor(ContextCompat.getColor(context, R.color.app_green));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        addView(textView);
        setOnClickListener(this);
    }

    /**
     * 设置标签
     *
     * @param item
     */
    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) return;
        selectItemChangeColorState();
        listener.onItemSelect(item);
    }

    protected void selectItemChangeColorState() {
        if (isItemSelected) {
            setBackgroundResource(R.drawable.bg_flow_unselect);
            textView.setTextColor(ContextCompat.getColor(context, R.color.app_green));
            isItemSelected = false;
        } else {
            setBackgroundResource(R.drawable.bg_flow_selected);
            textView.setTextColor(Color.WHITE);
            isItemSelected = true;
        }
    }
}
