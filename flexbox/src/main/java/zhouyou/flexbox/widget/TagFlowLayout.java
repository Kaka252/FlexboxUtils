package zhouyou.flexbox.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;

import zhouyou.flexbox.adapter.TagAdapter;

/**
 * 作者：ZhouYou
 * 日期：2017/3/28.
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
