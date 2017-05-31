package com.fupengpeng.designmodel.strategy.learning.context;

import com.fupengpeng.designmodel.strategy.learning.interf.IStrategy;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 策略容器
 */

public class Context {
    //持有策略接口（可接收策略实现）
    private IStrategy strategy;
    //构造函数，调用策略
    public Context(IStrategy strategy){
        this.strategy = strategy;
    }
    //使用策略
    public void operate(){
        //通过策略容器对象调用策略对象里面的策略
        this.strategy.operate();
    }
}
