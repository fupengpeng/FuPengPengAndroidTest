package com.fupengpeng.designmodel.abstractfactory.learning.interf;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 * 工程接口，可以造不同性别的人，需要不同的生产线（不同的实现类）
 */

public interface HumanFactory {

    //制造黄色人类
    public Human createYellowHuman();
    //制造一个白色人类
    public Human createWhiteHuman();
    //制造一个黑色人类
    public Human createBlackHuman();
}
