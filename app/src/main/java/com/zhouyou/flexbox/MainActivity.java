package com.zhouyou.flexbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import zhouyou.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhouyou.flexbox.widget.TagFlowLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCount;
    private StringTagAdapter adapter;

    private List<String> sourceData;
    private List<String> selectItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData() {
        sourceData = new ArrayList<>();
        sourceData.add("程序员");
        sourceData.add("设计师");
        sourceData.add("产品经理");
        sourceData.add("运营");
        sourceData.add("商务");
        sourceData.add("人事经理");
        sourceData.add("项目经理");
        sourceData.add("客户代表");
        sourceData.add("技术主管");
        sourceData.add("测试工程师");
        sourceData.add("前端工程师");
        sourceData.add("Java工程师");
        sourceData.add("Android工程师");
        sourceData.add("iOS工程师");

        selectItems = new ArrayList<>();
        selectItems.add("客户代表");
        selectItems.add("Java工程师");
    }

    private void initViews() {
        TagFlowLayout flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
        btnCount = (Button) findViewById(R.id.btn_get_count);
        adapter = new StringTagAdapter(this, sourceData, selectItems);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                btnCount.setText("已选择" + selectedItem.size() + "个");
            }
        });
        flowLayout.setAdapter(adapter);
        btnCount.setText("已选择" + adapter.getSelectedList().size() + "个");
        findViewById(R.id.btn_switch_data).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_data:
                List<String> data = new ArrayList<>();
                data.add("客户代表");
                data.add("Java工程师");

                List<String> selectList = new ArrayList<>();
                selectList.add("客户代表");
                adapter.setSource(data);
                adapter.setSelectItems(selectList);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
