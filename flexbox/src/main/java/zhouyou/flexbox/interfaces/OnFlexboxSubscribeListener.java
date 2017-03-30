package zhouyou.flexbox.interfaces;

import java.util.List;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27.
 */
public interface OnFlexboxSubscribeListener<T> {

    /**
     * @param selectedItem 已选中的标签
     */
    void onSubscribe(List<T> selectedItem);
}
