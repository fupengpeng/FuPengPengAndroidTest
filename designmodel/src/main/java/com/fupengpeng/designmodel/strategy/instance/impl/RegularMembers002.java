package com.fupengpeng.designmodel.strategy.instance.impl;

import com.fupengpeng.designmodel.strategy.instance.interf.IReturnCalculate;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 策略002
 */

public class RegularMembers002 implements IReturnCalculate {
    @Override
    public Double returnCalculate(Double originalPrice) {

        return originalPrice * 0.95;
    }
}
