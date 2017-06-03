package com.fupengpeng.selectallandinvertselect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    private ListView lv;
    private MyAdapter mAdapter;
    private ArrayList<String> list;
    private CheckBox checkBox_selectall;
    private CheckBox checkBox_cancel;
    private CheckBox checkBox_deselectall;
    private int checkNum; // 记录选中的条目数量
    private Button tv_show;// 用于显示选中的条目数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        		/* 实例化各个控件 */
        lv = (ListView) findViewById(R.id.lv_shangpinliebiao);
        checkBox_selectall = (CheckBox) findViewById(R.id.checkbox_quanxuan);
        checkBox_cancel = (CheckBox)  findViewById(R.id.checkbox_quxiaoxuanze);
        checkBox_deselectall = (CheckBox)  findViewById(R.id.checkbox_fanxuan);
        tv_show = (Button) findViewById(R.id.btn_jiesuanshuliang);
        list = new ArrayList<String>();

        // 为Adapter准备数据
        initDate();
        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter(list, this);

        // 绑定Adapter
        lv.setAdapter(mAdapter);

        checkBox_selectall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    // 遍历list的长度，将MyAdapter中的map值全部设为true
                    for (int i = 0; i < list.size(); i++) {
                        MyAdapter.getIsSelected().put(i, true);
                    }
                    // 数量设为list的长度
                    checkNum = list.size();
                    // 刷新listview和TextView的显示
                    dataChanged();
                }else {
                    // 遍历list的长度，将MyAdapter中的map值全部设为true
                    for (int i = 0; i < list.size(); i++) {
                        MyAdapter.getIsSelected().put(i, false);
                    }
                    // 数量设为list的长度
                    checkNum = list.size();
                    // 刷新listview和TextView的显示
                    dataChanged();
                }
            }
        });

//        // 全选按钮的回调接口
//        checkBox_selectall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 遍历list的长度，将MyAdapter中的map值全部设为true
//                for (int i = 0; i < list.size(); i++) {
//                    MyAdapter.getIsSelected().put(i, true);
//                }
//                // 数量设为list的长度
//                checkNum = list.size();
//                // 刷新listview和TextView的显示
//                dataChanged();
//            }
//        });

        // 反选按钮的回调接口
        checkBox_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        MyAdapter.getIsSelected().put(i, true);
                        checkNum++;
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 取消按钮的回调接口
        checkBox_deselectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的按钮设为未选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;// 数量减1
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 绑定listView的监听器
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(arg2, holder.cb.isChecked());
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                // 用TextView显示
                tv_show.setText("已选中" + checkNum + "项");
            }
        });



    }

    // 初始化数据
    private void initDate() {
        for (int i = 0; i < 50; i++) {
            list.add("data" + " " + i);
        }
        ;
    }
    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tv_show.setText("已选中" + checkNum + "项");
    };
}
