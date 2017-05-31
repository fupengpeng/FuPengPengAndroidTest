package com.fupengpeng.designmodel.proxy.learning.use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fupengpeng.designmodel.R;
import com.fupengpeng.designmodel.proxy.learning.impl.WangPo;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        proxy();

    }

    private void proxy() {
        //初始化代理对象
        WangPo wangPo = new WangPo();//

        /*
            使用被代理对象调用自身makeEyesWithMan方法（
            在此方法中使用代理对象调用被代理对象（接口实例--被代理对象）的makeEyesWithMan方法）
         */
        wangPo.makeEyesWithMan();
    }
}
