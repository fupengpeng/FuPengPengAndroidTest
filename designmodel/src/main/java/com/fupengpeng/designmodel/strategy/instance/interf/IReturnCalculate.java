package com.fupengpeng.designmodel.strategy.instance.interf;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 价格策略
 */

public interface IReturnCalculate {
    //根据不同的会员级别，实现获取价格方法
    Double returnCalculate(Double originalPrice);
}
