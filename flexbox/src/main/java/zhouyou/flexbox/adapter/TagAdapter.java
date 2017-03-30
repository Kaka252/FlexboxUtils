package zhouyou.flexbox.adapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zhouyou.flexbox.widget.BaseTagView;
import zhouyou.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhouyou.flexbox.widget.TagFlowLayout;
import zhouyou.flexbox.interfaces.TagWithListener;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27.
 */
public abstract class TagAdapter<V extends BaseTagView<T>, T> {

    private Context context;
    /**
     * 根布局
     */
    private TagFlowLayout rootView;

    /**
     * 数据源
     */
    private List<T> source;

    /**
     * 已选项目
     */
    private List<T> selectItems;
    /*view和tag的对应关系*/
    private Map<V, T> viewMap;
    /*标签选择操作的订阅接口*/
    private OnFlexboxSubscribeListener<T> onSubscribeListener;
    /*是否展示选中效果*/
    private boolean isShowHighlight = true;
    /*可选标签的最大数量*/
    private int maxSelection;
    /*默认和已选的背景*/
    protected int itemDefaultDrawable;
    protected int itemSelectDrawable;
    /*默认和已选的文字颜色*/
    protected int itemDefaultTextColor;
    protected int itemSelectTextColor;
    /*操作模式 0 - 多选 | 1 - 单选*/
    private int mode;

    public void setOnSubscribeListener(OnFlexboxSubscribeListener<T> onSubscribeListener) {
        this.onSubscribeListener = onSubscribeListener;
    }

    public void setSource(List<T> source) {
        this.source = source;
    }

    public void setSelectItems(List<T> selectItems) {
        this.selectItems = selectItems;
    }

    public List<T> getSelectItems() {
        return selectItems;
    }

    public TagAdapter(Context context, List<T> source) {
        this.context = context;
        this.source = source;
        viewMap = new ArrayMap<>();
    }

    public TagAdapter(Context context, List<T> source, List<T> selectItems) {
        this.context = context;
        this.source = source;
        this.selectItems = selectItems;
        viewMap = new ArrayMap<>();
    }

    public Context getContext() {
        return context;
    }

    public List<T> getData() {
        return source;
    }

    /**
     * 绑定控件
     *
     * @param rootView
     */
    public void bindView(TagFlowLayout rootView) {
        if (rootView == null) {
            throw new NullPointerException("未初始化TagFlowLayout");
        }
        this.rootView = rootView;
        isShowHighlight = rootView.isShowHighlight();
        itemDefaultDrawable = rootView.getItemDefaultDrawable();
        itemSelectDrawable = rootView.getItemSelectDrawable();
        itemDefaultTextColor = rootView.getItemDefaultTextColor();
        itemSelectTextColor = rootView.getItemSelectTextColor();
        maxSelection = rootView.getMaxSelection();
        mode = rootView.getMode();
    }

    /**
     * 设置标签组
     */
    public void addTags() {
        if (source == null || source.size() <= 0) return;
        rootView.removeAllViews();
        for (T item : source) {
            if (item == null) continue;
            final BaseTagView<T> view = addTag(item);
            initSelectedViews((V) view);
            // 单个item的点击监控
            view.setListener(new TagWithListener<T>() {
                @Override
                public void onItemSelect(T item) {
                    if (mode == TagFlowLayout.MODE_SINGLE_SELECT) {
                        if (isShowHighlight) view.selectItemChangeColorState();
                        singleSelectMode(item);
                    } else {
                        List<T> selectList = getSelectedList();
                        if ((maxSelection <= selectList.size() && maxSelection > 0) && !view.isItemSelected()) {
                            Toast.makeText(getContext(), "最多选择" + maxSelection + "个标签", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isShowHighlight) view.selectItemChangeColorState();
                    }
                    if (onSubscribeListener != null) {
                        onSubscribeListener.onSubscribe(getSelectedList());
                    }
                }
            });
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
        if (!isShowHighlight) return;
        if (selectItems == null || selectItems.size() <= 0) return;
        for (T select : selectItems) {
            if (checkIsItemNull(select)) continue;
            if (checkIsItemSame(view, select)) {
                view.setItemSelected(true);
                break;
            }
        }
    }

    /**
     * 单选操作模式
     */
    private void singleSelectMode(T item) {
        if (!isShowHighlight) return;
        for (BaseTagView<T> view : viewMap.keySet()) {
            if (checkIsItemSame((V) view, item)) {
                view.setItemSelected(true);
            } else {
                view.setItemSelected(false);
            }
        }
    }


    /**
     * 刷新数据
     */
    public void notifyDataSetChanged() {
        addTags();
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
        if (source == null) return 0;
        return source.size();
    }

    /**
     * 得到已选项目的列表
     *
     * @return
     */
    @SuppressWarnings("SuspiciousMethodCalls")
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
}
