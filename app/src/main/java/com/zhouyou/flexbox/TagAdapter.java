package com.zhouyou.flexbox;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27.
 */
public abstract class TagAdapter<V extends BaseTagView<T>, T> {

    private Context context;
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
    private Map<V, T> viewMap;

    /**
     * 标签选择操作的订阅接口
     */
    private OnFlexboxSubscribeListener<T> onSubscribeListener;

    /*默认和已选的背景*/
    int itemDefaultDrawable;
    int itemSelectDrawable;
    /*默认和已选的文字颜色*/
    int itemDefaultTextColor;
    int itemSelectTextColor;

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
            initSelectedViews((V) view);
            view.setListener(listener);
            viewMap.put((V) view, item);
            rootView.addView(view);
        }
    }

    /**
     * 设置在初始化时所选中的View
     *
     * @param view
     */
    private void initSelectedViews(V view) {
        if (selectItems != null && selectItems.size() > 0) {
            for (T select : selectItems) {
                if (checkIsItemNull(select)) continue;
                if (checkIsItemSame(view, select)) {
                    view.setItemSelected(true);
                    break;
                }
            }
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
    private void singleSelectMode(T item) {
        for (BaseTagView<T> view : viewMap.keySet()) {
            if (checkIsItemSame((V) view, item)) {
                view.setItemSelected(true);
            } else {
                view.setItemSelected(false);
            }
        }
    }

    /**
     * 对于相同item的判断条件
     *
     * @param view
     * @param item
     * @return
     */
    protected abstract boolean checkIsItemSame(V view, T item);

    /**
     * 检查item是否是空指针
     *
     * @param item
     * @return
     */
    protected abstract boolean checkIsItemNull(T item);

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
     * 得到已选项目的列表
     *
     * @return
     */
    public List<T> getSelectedList() {
        List<T> selectedList = new ArrayList<>();
        for (BaseTagView<T> view : viewMap.keySet()) {
            if (view.isItemSelected()) {
                T item = viewMap.get(view);
                selectedList.add(item);
            }
        }
        return selectedList;
    }

    public void setItemDefaultDrawable(int itemDefaultDrawable) {
        this.itemDefaultDrawable = itemDefaultDrawable;
    }

    public void setItemSelectDrawable(int itemSelectDrawable) {
        this.itemSelectDrawable = itemSelectDrawable;
    }

    public void setItemDefaultTextColor(int itemDefaultTextColor) {
        this.itemDefaultTextColor = itemDefaultTextColor;
    }

    public void setItemSelectTextColor(int itemSelectTextColor) {
        this.itemSelectTextColor = itemSelectTextColor;
    }
}
