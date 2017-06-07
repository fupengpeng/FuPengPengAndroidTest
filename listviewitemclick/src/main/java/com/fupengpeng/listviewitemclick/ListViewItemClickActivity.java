package com.fupengpeng.listviewitemclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fupengpeng.listviewitemclick.abstractimplements.IClick;
import com.fupengpeng.listviewitemclick.abstractimplements.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewItemClickActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    // 模拟listview中加载的数据
    private static final String[] CONTENTS = { "北京", "上海", "广州", "深圳", "苏州",
            "南京", "武汉", "长沙", "杭州" };
    private List<String> contentList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item_click);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        contentList = new ArrayList<String>();
        for (int i = 0; i < CONTENTS.length; i++) {
            contentList.add(CONTENTS[i]);
        }
        mListView.setAdapter(new ContentAdapter(this, contentList));
        mListView.setOnItemClickListener(this);
    }

    /**
     * 响应ListView的条目点击事件
     */
    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
        Toast.makeText(this, "点击的条目位置是-->" + position, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * 接口方法，响应ListView按钮点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(
                        ListViewItemClickActivity.this,
                        "listview的内部的评论按钮被点击了！，位置是-->" + (Integer) v.getTag()
                                + ",内容是-->" + contentList.get((Integer) v.getTag()),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(
                        ListViewItemClickActivity.this,
                        "listview的内部的分享按钮被点击了！，位置是-->" + (Integer) v.getTag()
                                + ",内容是-->" + contentList.get((Integer) v.getTag()),
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
