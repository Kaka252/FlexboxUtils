# FlexboxUtils
关于Flexbox的封装类，用于展示标签和操作标签

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
在Activity中声明如下代码，并通过以上定义好的adapter从而完成对标签的样式的设置
```
    StringTagAdapter adapter = new StringTagAdapter(this, list, selectItems);
    adapter.setItemDefaultDrawable(R.drawable.bg_flow_unselect);
    adapter.setItemSelectDrawable(R.drawable.bg_flow_selected);
    adapter.setItemDefaultTextColor(ContextCompat.getColor(this, R.color.app_green));
    adapter.setItemSelectTextColor(Color.WHITE);
```
# 回调
可以使用如下回调来获取最终所选择的项目列表
```
    StringTagAdapter adapter = new StringTagAdapter(this, list, selectItems);
    adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
        @Override
        public void onSubscribe(List<String> selectedItem) {
        }
    });
```
# 操作模式
可以通过设置模式来控制标签的单选与多选操作
```
    StringTagAdapter adapter = new StringTagAdapter(this, list, selectItems);
    adapter.setMode(TagAdapter.MODE_SINGLE_SELECT);
```
# 切换、刷新数据
在声明的adapter基础上，重新设置数据源和已选项，notifyDataSetChanged()方法并传入TagFlowLayout参数即可完成数据刷新操作
```
    ...
    adapter.setSource(data);
    adapter.setSelectItems(selectItems);
    adapter.notifyDataSetChanged(flowLayout);
```
# 绑定数据到控件
通过声明TagFlowLayout，并且调用setAdapter()方法来接收之前定义好的adapter即可
```
    TagFlowLayout flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
    flowLayout.setAdapter(adapter);
```
