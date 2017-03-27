package com.zhouyou.flexbox;

import java.util.List;

/**
 * 作者：ZhouYou
 * 日期：2017/3/27.
 */
public interface OnFlexboxSubscribeListener<T> {

    void onSubscribe(List<T> selectedItem);
}
