package com.fupengpeng.designmodel.strategy.learning.use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fupengpeng.designmodel.R;
import com.fupengpeng.designmodel.strategy.learning.context.Context;
import com.fupengpeng.designmodel.strategy.learning.impl.BackDoor;
/*
    策略模式
        作用：针对同一事件的不同情况的不同处理方法，实现对事件的处理和控制
        
        
 */
// TODO: 2017/5/27 0027   待深入研究OCP原则
public class StrategyPatternActivity extends AppCompatActivity {

    /**
     * 策略容器，调用策略方法
     */
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_pattern);

        //使用策略
        useStrategy();

    }

    //使用策略
    private void useStrategy() {
        //实例化策略容器，传入策略接口的实现类（策略001）
        context = new Context(new BackDoor());
        //调用策略容器的方法（operate方法中又调用策略接口实现类的实现方法）
        context.operate();
    }
}
