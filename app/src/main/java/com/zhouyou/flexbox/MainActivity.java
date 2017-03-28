package com.zhouyou.flexbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnCount = (Button) findViewById(R.id.btn_get_count);

        List<String> list = new ArrayList<>();
        list.add("程序员");
        list.add("设计师");
        list.add("产品经理");
        list.add("运营");
        list.add("商务");
        list.add("人事经理");
        list.add("项目经理");
        list.add("客户代表");
        list.add("技术主管");
        list.add("测试工程师");
        list.add("前端工程师");
        list.add("Java工程师");
        list.add("Android工程师");
        list.add("iOS工程师");

        List<String> selectItems = new ArrayList<>();
        selectItems.add("客户代表");
        selectItems.add("Java工程师");

        final StringTagAdapter adapter = new StringTagAdapter(this, list, selectItems);
        adapter.setMode(TagAdapter.MODE_SINGLE_SELECT);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                btnCount.setText("已选择" + selectedItem.size() + "个");
            }
        });
        TagFlowLayout flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
        flowLayout.setAdapter(adapter);
        btnCount.setText("已选择" + adapter.getSelectedList().size() + "个");
    }
}
