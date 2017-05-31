package com.fupengpeng.designmodel.factorymethod.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fupengpeng.designmodel.R;
import com.fupengpeng.designmodel.factorymethod.learning.impl.WhiteHuman;
import com.fupengpeng.designmodel.factorymethod.learning.interf.Human;
import com.fupengpeng.designmodel.factorymethod.learning.use.HumanFactory;

/**
 *
 * 4.在实际中应用工厂生产对象加以使用
 * 工厂方法模式
 *     1.定义一个类型接口
 *     2.具体的类实现这一接口
 *     3.写个一工厂，提供一个方法用于根据传入的实现类来生产出一个实现类对象
 *     4.在实际中应用工厂生产对象加以使用
 */
public class FactoryMethodActivity extends AppCompatActivity {


    private Human whiteHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_method);

        first();

    }
    private void first() {
        //根据需求调用Activity里面的犯法
        whiteHuman = HumanFactory.createHuman(WhiteHuman.class);

    }
}
