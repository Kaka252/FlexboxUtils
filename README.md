# FlexboxUtils
Google在之前发布了一个叫做FlexboxLayout的控件，完全能够适配平时我们业务中自定义的布局流控件，而且有过之而无不及。因此在这个基础上我又针对这个控件进行了进一步的封装，基本能够满足平时大部分的业务需求，先上图
![preview](https://github.com/Kaka252/FlexboxUtils/blob/master/screenshot/device-2017-03-28-181547.png?raw=true)

# 继承BaseTagView
定义一个TagView，继承BaseTagView，由于每一个tag的数据类型不确定，因此需要传入一个固定的数据类型满足实际需求
```
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
}
```

# 继承TagAdapter
定义一个Adapter，继承TagAdapter，并实现相应的方法
```
public class StringTagAdapter extends TagAdapter<StringTagView, String> {

    StringTagAdapter(Context context, List<String> data) {
        this(context, data, null);
    }

    StringTagAdapter(Context context, List<String> data, List<String> selectItems) {
        super(context, data, selectItems);
    }

    /**
     * 检查item和所选item是否一样
     *
     * @param view
     * @param item
     * @return
     */
    @Override
    protected boolean checkIsItemSame(StringTagView view, String item) {
        return TextUtils.equals(view.getItem(), item);
    }

    /**
     * 检查item是否是空指针
     *
     * @return
     */
    @Override
    protected boolean checkIsItemNull(String item) {
        return TextUtils.isEmpty(item);
    }

    /**
     * 添加标签
     *
     * @param item
     * @return
     */
    @Override
    protected StringTagView addTag(String item) {
        StringTagView tagView = new StringTagView(getContext());
        tagView.setPadding(20, 20, 20, 20);

        TextView textView = tagView.getTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setGravity(Gravity.CENTER);

        tagView.setItemDefaultDrawable(itemDefaultDrawable);
        tagView.setItemSelectDrawable(itemSelectDrawable);
        tagView.setItemDefaultTextColor(itemDefaultTextColor);
        tagView.setItemSelectTextColor(itemSelectTextColor);
        tagView.setItem(item);
        return tagView;
    }
}
```
# 使用
在xml中，你可以使用如下配置:
```
<resources>
    <declare-styleable name="TagFlowLayout">
        <attr name="showHighlight" format="boolean" /> // 是否选中高亮
        <attr name="defaultDrawable" format="reference" /> // 默认标签背景
        <attr name="selectDrawable" format="reference" /> // 选中标签背景
        <attr name="defaultTextColor" format="color|reference"/> // 默认标签文字颜色
        <attr name="selectTextColor" format="color|reference" /> // 选中标签文字颜色
        <attr name="mode"> // 单选或者多选
            <enum name="MULTI" value="0" /> // 默认多选
            <enum name="SINGLE" value="1"/> // 单选
        </attr>
    </declare-styleable>
</resources>
```
```
    <zhouyou.flexbox.widget.TagFlowLayout
        android:id="@+id/flow_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:alignContent="flex_start"
        app:alignItems="center"
        app:dividerDrawable="@drawable/bg_flow_divider"
        app:flexDirection="row"
        app:flexWrap="wrap"
        app:justifyContent="flex_start"
        app:showDivider="beginning|middle|end"
        app:selectDrawable="@drawable/bg_flow_selected"
        app:defaultDrawable="@drawable/bg_flow_unselect"
        app:selectTextColor="@android:color/white"
        app:defaultTextColor="@color/app_green"
        app:mode="SINGLE"/>
```
也可以使用Java代码进行属性配置
```
    TagFlowLayout flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
    flowLayout.setShowHighlight(false);
    flowLayout.setItemDefaultDrawable(R.drawable.bg_flow_unselect);
    flowLayout.setItemSelectDrawable(R.drawable.bg_flow_selected);
    flowLayout.setItemDefaultTextColor(ContextCompat.getColor(this, R.color.app_green));
    flowLayout.setItemSelectTextColor(Color.WHITE);
    flowLayout.setMode(TagFlowLayout.MODE_SINGLE_SELECT);
```
# 回调
可以使用如下回调来获取最终所选择的项目列表
```
    ...
    adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
        @Override
        public void onSubscribe(List<String> selectedItem) {
        }
    });
```
# 操作模式
可以通过设置模式来控制标签的单选与多选操作
```
    ...
    flowLayout.setMode(TagFlowLayout.MODE_SINGLE_SELECT);
```
# 选中高亮效果
可以设置是否选中高亮，默认为选中高亮
```
    ...
    flowLayout.setShowHighlight(false);
```

# 绑定数据到控件
通过声明TagFlowLayout，并且调用setAdapter()方法来接收之前定义好的adapter即可
```
    ...
    flowLayout.setAdapter(adapter);
```
# 切换、刷新数据
在声明的adapter基础上，重新设置数据源和已选项，notifyDataSetChanged()方法即可完成数据刷新操作
```
    ...
    adapter.setSource(data);
    adapter.setSelectItems(selectItems);
    adapter.notifyDataSetChanged();
```
