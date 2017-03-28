package com.zhouyou.flexbox;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.util.List;
import java.util.Map;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27.
 */
public abstract class TagAdapter<V extends BaseTagView<T>, T> {

    private Context context;

    private int itemDefaultDrawable;
    private int itemSelectDrawable;
    /**
     * 数据源
     */
    private List<T> source;

    /**
     * 已选项目
     */
    private List<T> selectItems;

    /**
     * view和tag的对应关系
     */
    Map<V, T> viewMap;

    /**
     * 标签选择操作的订阅接口
     */
    private OnFlexboxSubscribeListener<T> onSubscribeListener;

    public void setItemDefaultDrawable(int itemDefaultDrawable) {
        this.itemDefaultDrawable = itemDefaultDrawable;
    }

    public void setItemSelectDrawable(int itemSelectDrawable) {
        this.itemSelectDrawable = itemSelectDrawable;
    }

    /**
     * 操作模式 0 - 多选 | 1 - 单选
     */
    private int mode = MODE_MULTI_SELECT;
    public static final int MODE_MULTI_SELECT = 0;
    public static final int MODE_SINGLE_SELECT = 1;

    void setOnSubscribeListener(OnFlexboxSubscribeListener<T> onSubscribeListener) {
        this.onSubscribeListener = onSubscribeListener;
    }

    public void setSelectItems(List<T> selectItems) {
        this.selectItems = selectItems;
    }

    public List<T> getSelectItems() {
        return selectItems;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    TagAdapter(Context context, List<T> source) {
        this.context = context;
        this.source = source;
        viewMap = new ArrayMap<>();
    }

    TagAdapter(Context context, List<T> source, List<T> selectItems) {
        this.context = context;
        this.source = source;
        this.selectItems = selectItems;
        viewMap = new ArrayMap<>();
    }

    Context getContext() {
        return context;
    }

    public List<T> getData() {
        return source;
    }

    /**
     * 设置标签组
     *
     * @param rootView
     */
    void addTags(TagFlowLayout rootView) {
        for (T item : source) {
            if (item == null) continue;
            final BaseTagView<T> view = addTag(item);
            view.setListener(listener);
            viewMap.put((V) view, item);
            rootView.addView(view);
        }
    }

    /**
     * 点击标签的回调
     */
    private TagWithListener<T> listener = new TagWithListener<T>() {
        @Override
        public void onItemSelect(T item) {
            if (mode == MODE_SINGLE_SELECT) {
                singleSelectMode(item);
            }
            if (onSubscribeListener != null) {
                onSubscribeListener.onSubscribe(getSelectedList());
            }
        }
    };

    /**
     * 单选操作模式
     */
    protected abstract void singleSelectMode(T item);

    /**
     * 添加单个标签
     *
     * @param item
     * @return
     */
    protected abstract BaseTagView<T> addTag(T item);

    /**
     * 获取所有item的数量
     */
    protected int getCount() {
        return source.size();
    }

    /**
     * 已选择的item列表
     *
     * @return
     */
    protected abstract List<T> getSelectedList();
}
