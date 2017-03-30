package zhouyou.flexbox.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;

import zhouyou.flexbox.R;
import zhouyou.flexbox.adapter.TagAdapter;

/**
 * 作者：ZhouYou
 * 日期：2017/3/28.
 */
public class TagFlowLayout extends FlexboxLayout {

    /*是否展示选中效果*/
    private boolean isShowHighlight = true;
    /*默认和已选的背景*/
    private int itemDefaultDrawable;
    private int itemSelectDrawable;
    /*默认和已选的文字颜色*/
    private int itemDefaultTextColor;
    private int itemSelectTextColor;
    /*操作模式 0 - 多选 | 1 - 单选*/
    private int mode = MODE_MULTI_SELECT;
    /*可选标签的最大数量*/
    private int maxSelection;
    public static final int MODE_MULTI_SELECT = 0;
    public static final int MODE_SINGLE_SELECT = 1;

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
        isShowHighlight = ta.getBoolean(R.styleable.TagFlowLayout_showHighlight, true);
        itemDefaultDrawable = ta.getResourceId(R.styleable.TagFlowLayout_defaultDrawable, 0);
        itemSelectDrawable = ta.getResourceId(R.styleable.TagFlowLayout_selectDrawable, 0);
        itemDefaultTextColor = ta.getColor(R.styleable.TagFlowLayout_defaultTextColor, 0);
        itemSelectTextColor = ta.getColor(R.styleable.TagFlowLayout_selectTextColor, 0);
        mode = ta.getInt(R.styleable.TagFlowLayout_mode, MODE_MULTI_SELECT);
        maxSelection = ta.getInt(R.styleable.TagFlowLayout_maxSelectionCount, 0);
        ta.recycle();
    }

    public void setAdapter(TagAdapter adapter) {
        if (adapter == null) {
            removeAllViews();
            return;
        }
        adapter.bindView(this);
        adapter.addTags();
    }

    public boolean isShowHighlight() {
        return isShowHighlight;
    }

    public void setShowHighlight(boolean showHighlight) {
        isShowHighlight = showHighlight;
    }

    public int getItemDefaultDrawable() {
        return itemDefaultDrawable;
    }

    public void setItemDefaultDrawable(int itemDefaultDrawable) {
        this.itemDefaultDrawable = itemDefaultDrawable;
    }

    public int getItemSelectDrawable() {
        return itemSelectDrawable;
    }

    public void setItemSelectDrawable(int itemSelectDrawable) {
        this.itemSelectDrawable = itemSelectDrawable;
    }

    public int getItemDefaultTextColor() {
        return itemDefaultTextColor;
    }

    public void setItemDefaultTextColor(int itemDefaultTextColor) {
        this.itemDefaultTextColor = itemDefaultTextColor;
    }

    public int getItemSelectTextColor() {
        return itemSelectTextColor;
    }

    public void setItemSelectTextColor(int itemSelectTextColor) {
        this.itemSelectTextColor = itemSelectTextColor;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMaxSelection() {
        return maxSelection;
    }

    public void setMaxSelection(int maxSelection) {
        this.maxSelection = maxSelection;
    }
}
