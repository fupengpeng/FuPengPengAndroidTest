package com.fupengpeng.butterknife.activity.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fupengpeng.butterknife.R;
import com.fupengpeng.butterknife.customview.view.ChooseAddressWheel;
import com.fupengpeng.butterknife.customview.view.listener.OnAddressChangeListener;
import com.fupengpeng.butterknife.entity.Data;
import com.fupengpeng.butterknife.utils.JsonUtil;
import com.fupengpeng.butterknife.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ButterKnife 用法说明
 * 1.model的build.grade添加依赖：
 * apt 'com.jakewharton:butterknife-compiler:8.1.0'//增加这一句
 * 2.model的build.grade下添加
 * apply plugin: 'com.neenbedankt.android-apt'//增加这一句
 * 3.工程项目build.grade添加依赖
 * classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'.
 * 4.具体用法详见代码中
 * butterknife zelezny
 */
public class ButterKnifeActivity extends AppCompatActivity implements OnAddressChangeListener {

    @BindView(R.id.choose_address)
    TextView chooseAddress;

    private ChooseAddressWheel chooseAddressWheel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initWheel();
        initData();
    }

    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(this);
    }

    private void initData() {
        String address = Utils.readAssert(this, "address.txt");
        Data data = JsonUtil.parseJson(address, Data.class);
        if (data != null) {
            if (data == null) return;
            chooseAddress.setText(data.getProvince().get(0).getName() + " " +
                    data.getProvince().get(0).getCity().get(0).getName() + " " +
                    data.getProvince().get(0).getCity().get(0).getArea().get(0).getName());
            if (data.getProvince() != null) {
                // 设置省份列表
                chooseAddressWheel.setProvince(data.getProvince());
                // 设置默认开始显示地址
                chooseAddressWheel.defaultValue(data.getProvince().get(0).getName(),
                        data.getProvince().get(0).getCity().get(0).getName(),
                        data.getProvince().get(0).getCity().get(0).getArea().get(0).getName());
            }
        }
    }

    @OnClick(R.id.choose_address)
    public void addressClick(View view) {
        Utils.hideKeyBoard(this);
        chooseAddressWheel.show(view);
    }

    @Override
    public void onAddressChange(String province, String city, String district, String address) {
        chooseAddress.setText(province + " " + city + " " + district + " " + address);
    }
}
