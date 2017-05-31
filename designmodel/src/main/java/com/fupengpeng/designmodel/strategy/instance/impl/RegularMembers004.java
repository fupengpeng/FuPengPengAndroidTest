package com.fupengpeng.designmodel.strategy.instance.impl;

import com.fupengpeng.designmodel.strategy.instance.interf.IReturnCalculate;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 策略004
 */

public class RegularMembers004 implements IReturnCalculate {
    @Override
    public Double returnCalculate(Double originalPrice) {

        return originalPrice * 0.85;
    }
}
